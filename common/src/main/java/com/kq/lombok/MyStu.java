package com.kq.lombok;

/**
 * MyStu
 * @author kq
 * @date 2019-09-03
 */

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MyStu {

    @NonNull
    private Long id;
    private final String name;
    private String address;


}
