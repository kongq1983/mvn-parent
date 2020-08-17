package com.kq.util;

/**
 * Twitter的分布式自增ID雪花算法snowflake
 * @author kq
 * @create 2019-09-01
 *
 * 0 00000000000000000000000000000000000000000 00000 00000 000000000000
 * 1 41 5 5 12 (位数)
 * 以0开头
 * 41位代码  当前时间-开始时间的毫秒数
 * 5 高位5bit是数据中心ID（datacenterId）
 * 5 低位5bit是工作节点ID（workerId）
 * 12 12位（bit）可以表示的最大正整数是 2^12 - 1 = 4095，即一个节点在一毫秒内可以累加产生4095个ID序号
 **/
public class SnowFlake {

    /**
     * 起始的时间戳
     * 2019-09-01 00:00:00
     */
    private final static long START_STMP = 1567267200000L;

    /**
     * 每一部分占用的位数
     */
    private final static long SEQUENCE_BIT = 12; //序列号占用的位数
    private final static long MACHINE_BIT = 5;   //机器标识占用的位数
    private final static long DATACENTER_BIT = 5;//数据中心占用的位数

    /**
     * 每一部分的最大值 31
     */
    private final static long MAX_DATACENTER_NUM = -1L ^ (-1L << DATACENTER_BIT);
    /** 31 */
    private final static long MAX_MACHINE_NUM = -1L ^ (-1L << MACHINE_BIT);
    /** 4095 */
    private final static long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);

    /**
     * 每一部分向左的位移 12
     */
    private final static long MACHINE_LEFT = SEQUENCE_BIT;
    /** 17 */
    private final static long DATACENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    /** 22 */
    private final static long TIMESTMP_LEFT = DATACENTER_LEFT + DATACENTER_BIT;

    private long datacenterId;  //数据中心
    private long machineId;     //机器标识
    private long sequence = 0L; //序列号
    private long lastStmp = -1L;//上一次时间戳

    public SnowFlake(long datacenterId, long machineId) {
        if (datacenterId > MAX_DATACENTER_NUM || datacenterId < 0) {
            throw new IllegalArgumentException("datacenterId can't be greater than MAX_DATACENTER_NUM or less than 0");
        }
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException("machineId can't be greater than MAX_MACHINE_NUM or less than 0");
        }
        this.datacenterId = datacenterId;
        this.machineId = machineId;
    }

    /**
     * 产生下一个ID
     *
     * @return
     */
    public synchronized long nextId() {
        long currStmp = getNewstmp();
        if (currStmp < lastStmp) {
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
        }

        if (currStmp == lastStmp) {
            //相同毫秒内，序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;
            //同一毫秒的序列数已经达到最大
            if (sequence == 0L) {
                currStmp = getNextMill();
            }
        } else {
            //不同毫秒内，序列号置为0
            sequence = 0L;
        }

        lastStmp = currStmp;

        return (currStmp - START_STMP) << TIMESTMP_LEFT //时间戳部分
                | datacenterId << DATACENTER_LEFT       //数据中心部分
                | machineId << MACHINE_LEFT             //机器标识部分
                | sequence;                             //序列号部分
    }

    private long getNextMill() {
        long mill = getNewstmp();
        while (mill <= lastStmp) {
            mill = getNewstmp();
        }
        return mill;
    }

    private long getNewstmp() {
        return System.currentTimeMillis();
    }

    public static void main1(String[] args) {

        System.out.println(System.currentTimeMillis());

        SnowFlake snowFlake = new SnowFlake(1, 1);

        long start = System.currentTimeMillis();
        int size = 10;
        for (int i = 0; i < size; i++) {
            System.out.println(snowFlake.nextId());
        }

        System.out.println(System.currentTimeMillis() - start);


    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        System.out.println("MAX_DATACENTER_NUM="+MAX_DATACENTER_NUM);
        System.out.println("MAX_MACHINE_NUM="+MAX_MACHINE_NUM);
        System.out.println("MAX_SEQUENCE="+MAX_SEQUENCE);
        System.out.println("16向右移动1位="+(16>>1));
        System.out.println("1的向左移动12位="+(1 << 12));
        System.out.println("异或="+(-1^(-1 << 12)));
        System.out.println("1的向左移动10位="+(1 << 10));


    }

}
