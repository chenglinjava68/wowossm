package cn.tf.wowossm.service.impl;

import java.util.List;

import javax.crypto.EncryptedPrivateKeyInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import org.springframework.util.DigestUtils;

import cn.tf.wowossm.mapper.UserinfoMapper;
import cn.tf.wowossm.po.Userinfo;
import cn.tf.wowossm.po.UserinfoExample;
import cn.tf.wowossm.po.UserinfoExample.Criteria;
import cn.tf.wowossm.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserinfoMapper userinfoMapper;
	
	
	@Override
	public int register(Userinfo user) {
		user.setPwd(DigestUtils.md5DigestAsHex(user.getPwd().getBytes()));
		//return userinfoMapper.insertSelective(user);
		return userinfoMapper.insert(user);
	}




	@Override
	public Userinfo login(Userinfo user) {
		UserinfoExample example=new UserinfoExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andPwdEqualTo(DigestUtils.md5DigestAsHex(user.getPwd().getBytes()));
		createCriteria.andUnameEqualTo(user.getUname());
		
		List<Userinfo> list = userinfoMapper.selectByExample(example);
		
		if(list!=null){
			return list.get(0);
		}
		return null;
	}




	@Override
	public boolean activeUser(Integer usId) {
		
		return userinfoMapper.updateUserStatus(usId);
	}

}
