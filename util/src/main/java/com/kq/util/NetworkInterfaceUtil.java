package com.kq.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;


public class NetworkInterfaceUtil {

    /*** 因为一台机器不一定只有一个网卡呀，所以返回的是数组是很合理的 ***/
    public static List<String> getMacList() throws Exception {
        Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
        StringBuilder sb = new StringBuilder();
        ArrayList<String> tmpMacList = new ArrayList<>();
        while (en.hasMoreElements()) {
            NetworkInterface iface = en.nextElement();
            List<InterfaceAddress> addrs = iface.getInterfaceAddresses();
            for (InterfaceAddress addr : addrs) {
                InetAddress ip = addr.getAddress();
                NetworkInterface network = NetworkInterface.getByInetAddress(ip);
                if (network == null) {
                    continue;
                }
                byte[] mac = network.getHardwareAddress();
                if (mac == null) {
                    continue;
                }
                sb.delete(0, sb.length());
                for (int i = 0; i < mac.length; i++) {
                    sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                }
                tmpMacList.add(sb.toString());
            }
        }
        if (tmpMacList.size() <= 0) {
            return tmpMacList;
        }
        /*** 去重，别忘了同一个网卡的ipv4,ipv6得到的mac都是一样的，肯定有重复，下面这段代码是。。流式处理 ***/
        List<String> unique = tmpMacList.stream().distinct().collect(Collectors.toList());
        return unique;
    }

    /**
     * 获取IP地址
     *
     * @return
     * @throws Exception
     */
    public static List<String> getIPList() throws Exception {
        List<String> ipList = new ArrayList<String>();
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        NetworkInterface networkInterface;
        Enumeration<InetAddress> inetAddresses;
        InetAddress inetAddress;
        String ip;
        while (networkInterfaces.hasMoreElements()) {
            networkInterface = networkInterfaces.nextElement();
            inetAddresses = networkInterface.getInetAddresses();
            while (inetAddresses.hasMoreElements()) {
                inetAddress = inetAddresses.nextElement();
                if (inetAddress != null && inetAddress instanceof Inet4Address) { // IPV4
                    ip = inetAddress.getHostAddress();
                    ipList.add(ip);
                }
            }
        }

        return ipList;
    }

    /**
     * 获取本机ip
     * @return
     * @throws Exception
     */
    public static String getLocalIp() throws Exception {
        InetAddress ia = InetAddress.getLocalHost();
        String localName = ia.getHostName();
        String localIp = ia.getHostAddress();
        System.out.println("本机名称是：" + localName);
        System.out.println("本机的ip是 ：" + localIp);
        return localIp;

    }

    public static void main(String[] args) throws Exception {
        System.out.println("进行 multi net address 测试===》");
        List<String> macs = getMacList();
        System.out.println("本机的mac网卡的地址有：" + macs);

        List<String> ipList = getIPList();
        System.out.println("本机的ip地址有：" + ipList);

        String localIp = getLocalIp();
        System.out.println("本机ip：" + localIp);
    }

}
