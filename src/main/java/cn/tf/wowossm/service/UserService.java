package cn.tf.wowossm.service;

import cn.tf.wowossm.po.Userinfo;

public interface UserService {

	int register(Userinfo user);

	Userinfo login(Userinfo user);

}
