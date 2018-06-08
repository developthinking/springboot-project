package com.wuti.demo.mapper;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wuti.demo.entity.UserEntity;
import com.wuti.demo.enums.UserSexEnum;
import com.wuti.demo.param.UserParam;
import com.wuti.demo.result.Page;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
	
	@Resource
	private UserMapper userMapper;
	
	@Test
	public void testInsert() {
		userMapper.insert(new UserEntity("aa", "a123456", UserSexEnum.MAN));
		userMapper.insert(new UserEntity("bb", "b123456", UserSexEnum.WOMAN));
		userMapper.insert(new UserEntity("cc", "c123456", UserSexEnum.WOMAN));
		Assert.assertEquals(3, userMapper.getAll().size());
	}
	
	@Test
	public void testQuery() {
		List<UserEntity> users = userMapper.getAll();
		if(users==null || users.size()==0){
			System.out.println("is null");
		}else{
			System.out.println(users.toString());
		}
	}
	
	@Test
	public void testUpdate() {
		long id = 30l;
		UserEntity user = userMapper.getOne(id);
		if(user != null){
			System.out.println(user.toString());
			user.setNickName("wt");
			userMapper.update(user);
			Assert.assertTrue(("neo".equals(userMapper.getOne(id).getNickName())));
		} else {
			System.out.println("not find user id="+id);
		}
	}
	
	@Test
	public void testDelete() {
		int count=userMapper.delete(29l);
		if(count>0){
			System.out.println("delete is sucess");
		}else {
			System.out.println("delete if failed");
		}
	}
	
	@Test
	public void testPage() {
		UserParam userParam=new UserParam();
		userParam.setUserSex("WOMAN");
		userParam.setCurrentPage(0);
		List<UserEntity> users=userMapper.getList(userParam);
		long count=userMapper.getCount(userParam);
		Page page = new Page(userParam,count,users);
		System.out.println(page);
	}

}
