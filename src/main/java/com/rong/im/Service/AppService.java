package com.rong.im.Service;

import com.rong.im.Bean.App;

import java.util.Map;

public interface AppService {

    Map<String, Object> getByUid(Long uid);

    boolean insert(App app);

    boolean updateByUid(Long status, String name, String desc, Long uid);
}
