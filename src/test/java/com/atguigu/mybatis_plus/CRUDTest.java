package com.atguigu.mybatis_plus;

import com.atguigu.mybatis_plus.entity.User;
import com.atguigu.mybatis_plus.mapper.UserMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sound.midi.Soundbank;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CRUDTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsert(){

        User user = new User();
        //user.setId(300L);
        user.setName("Helen");
        user.setAge(18);
        user.setEmail("55317332@qq.com");

        int result = userMapper.insert(user);

        log.info("影响行数："+result);
        log.info("id:"+user.getId());


    }


    @Test
    public void testUpdateById(){

        User user = new User();
        user.setId(1227234964768915458L);
        user.setName("环环妹妹");
        user.setAge(18);
        user.setEmail("55317332@qq.com");

        int i = userMapper.updateById(user);

        log.info("影响行数："+i);

    }

    /**
     * 悲观锁：无论有没有并发线程，都把这段代码加上锁，避免并发
     * 乐观锁：认为没有并发，当出现并发时，再来解决
     */
    @Test
    public void ConcurrentUpdate(){
        //第一个用户：查询数据
        User user1 = userMapper.selectById(1L);
        //修改用户数据
        user1.setViewCount(user1.getViewCount()+1);
        //执行修改
        int result1 = userMapper.updateById(user1);
        log.info(result1>0 ? "user1更新成功":"user1更新失败");

//        //第二个用户
//        User user2 = userMapper.selectById(1L);
//        //修改用户数据
//        user2.setViewCount(user2.getViewCount()+1);
//        //执行修改
//        int result2 = userMapper.updateById(user2);
//        log.info(result2>0 ? "user2更新成功":"user2更新失败");
    }


    @Test
    public void testSelectBatchIds(){

        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        users.forEach(System.out::println);
    }

    @Test
    public void testSelectByMap(){

        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "Helen");
        map.put("age", 18);
        List<User> users = userMapper.selectByMap(map);

        users.forEach(System.out::println);
    }

    @Test
    public  void testSelectPage(){
        Page<User> page = new Page<>(1,5);
        IPage<User> page1 = userMapper.selectPage(page, null);

        List<User> records = page1.getRecords();
        records.forEach(System.out::println);
    }

    @Test
    public void testDeleteById(){

        int result = userMapper.deleteById(1227234964768915458L);
        System.out.println(result);
    }

    @Test
    public void testDeleteBatchIds() {

        int result = userMapper.deleteBatchIds(Arrays.asList(300));
        System.out.println(result);
    }

    @Test
    public void testDeleteByMap() {

        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "Helen");
        map.put("age", 18);

        int result = userMapper.deleteByMap(map);
        System.out.println(result);
    }

    @Test
    public void testLogicDelete(){
        int result = userMapper.deleteById(1L);

        System.out.println(result);

    }
}
