package com.example.macmini.dianshang;

import com.example.macmini.dianshang.JavaBean.Areas;
import com.example.macmini.dianshang.JavaBean.City;
import com.example.macmini.dianshang.JavaBean.Country;
import com.example.macmini.dianshang.JavaBean.User;

/**
 * Created by macmini on 2018/3/23.
 */

public interface TypeFactory {
    int type(User user);

    int type(Country country);

    int type(City city);

    int type(Areas areas);
}
