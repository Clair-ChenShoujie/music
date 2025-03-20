package com.example.yin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.yin.common.R;
import com.example.yin.constant.Constants;
import com.example.yin.mapper.ConsumerMapper;
import com.example.yin.model.domain.Consumer;
import com.example.yin.model.request.ConsumerRequest;
import com.example.yin.service.ConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.example.yin.constant.Constants.SALT;

@Slf4j
@Service
public class ConsumerServiceImpl extends ServiceImpl<ConsumerMapper, Consumer>
        implements ConsumerService {

    @Autowired
    private ConsumerMapper consumerMapper;


    /**
     * 用户注册，新增用户
     */

    @Override
    public R addUser(ConsumerRequest registryRequest) {

        //判断该用户名是否已经被注册
        if (this.existUser(registryRequest.getUsername())) {
            return R.warning("用户名已注册");
        }
        log.info("正在注册");

        Consumer consumer = new Consumer();
//        复制对象
        BeanUtils.copyProperties(registryRequest, consumer);

        System.out.println(consumer.toString());

        //MD5加密+盐值加密
        String password = DigestUtils.md5DigestAsHex((SALT + registryRequest.getPassword()).getBytes(StandardCharsets.UTF_8));
        //修改要加入数据库中的用户的密码，为加密后的密码
        consumer.setPassword(password);

        //判断电话号码或邮箱是否为空，为空就修改consumer中的相关属性
        if (StringUtils.isBlank(consumer.getPhoneNum())) {
            consumer.setPhoneNum(null);
        }
        if ("".equals(consumer.getEmail())) {
            consumer.setEmail(null);
        }

        //使用默认的用户图片
        consumer.setAvator("img/avatorImages/user.jpg");
        try {
            //将用户注册的信息添加入数据库中
            if (consumerMapper.insert(consumer) > 0) {
                return R.success("注册成功");
            } else {
                return R.error("注册失败");
            }
        } catch (DuplicateKeyException e) {
            return R.fatal(e.getMessage());
        }
    }

    /**
     * TODO 用户端调用
     * 用户修改个人信息
     */
    @Override
    public R updateUserMsg(ConsumerRequest updateRequest) {
        Consumer consumer = new Consumer();
        BeanUtils.copyProperties(updateRequest, consumer);

        //更新数据库中的信息
        if (consumerMapper.updateById(consumer) > 0) {
            return R.success("修改成功");
        } else {
            return R.error("修改失败");
        }
    }

    /**
     * TODO 用户端调用
     * 用户修改个人密码
     */
    @Override
    public R updatePassword(ConsumerRequest updatePasswordRequest) {
        //验证输入的旧密码是否是正确的
       if (!this.verityPasswd(updatePasswordRequest.getUsername(),updatePasswordRequest.getOldPassword())) {
            return R.error("密码输入错误");
        }

        //因为这里面ConsumerRequest的属性与Consumer中不一样（多了一个旧密码）
        //所以不能用BeanUtils.copyProperties来进行类型转换，只能手动添加属性
        Consumer consumer = new Consumer();
        consumer.setId(updatePasswordRequest.getId());
        String secretPassword = DigestUtils.md5DigestAsHex((SALT + updatePasswordRequest.getPassword()).getBytes(StandardCharsets.UTF_8));
        consumer.setPassword(secretPassword);

        //根据主键来修改
        if (consumerMapper.updateById(consumer) > 0) {
            return R.success("密码修改成功");
        } else {
            return R.error("密码修改失败");
        }
    }

    /**
     * TODO 用户端调用
     * 用户更新个人头像
     */
    @Override
    public R updateUserAvator(MultipartFile avatorFile, int id) {
        //获取文件名
        String fileName = System.currentTimeMillis() + avatorFile.getOriginalFilename();
        //获取路径 根据你的系统获取对应的文件分隔符
        String filePath = Constants.ASSETS_PATH + System.getProperty("file.separator") + "img" + System.getProperty("file.separator") + "avatorImages";
        File file = new File(filePath);
        if (!file.exists() && !file.mkdir()) {
            return R.fatal("创建文件失败");
        }
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String imgPath = "/img/avatorImages/" + fileName;
        try {
            avatorFile.transferTo(dest);
        } catch (IOException e) {
            return R.fatal("上传失败" + e.getMessage());
        }
        Consumer consumer = new Consumer();
        consumer.setId(id);
        consumer.setAvator(imgPath);
        if (consumerMapper.updateById(consumer) > 0) {
            return R.success("上传成功", imgPath);
        } else {
            return R.error("上传失败");
        }
    }


    /**
     * TODO addUser方法调用
     * 用数据库中判断是否存在该用户名相同
     */
    @Override
    public boolean existUser(String username) {
        QueryWrapper<Consumer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        return consumerMapper.selectCount(queryWrapper) > 0;
    }

    /**
     * TODO 验证密码和用户名
     * 登录和修改密码判断
     */

    @Override
    public boolean verityPasswd(String username, String password) {
        QueryWrapper<Consumer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        String secretPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes(StandardCharsets.UTF_8));
        queryWrapper.eq("password",secretPassword);
        return consumerMapper.selectCount(queryWrapper) > 0;
    }

    /**
     * TODO 管理员端删除用户
     */
    @Override
    public R deleteUser(Integer id) {
        if (consumerMapper.deleteById(id) > 0) {
            return R.success("删除成功");
        } else {
            return R.error("删除失败");
        }
    }

    /**
     * TODO 管理员调用数据库获取所有用户信息
     * 获取用户信息
     */
    @Override
    public R allUser() {
        return R.success(null, consumerMapper.selectList(null));
    }

    /**
     * TODO 获取用户的个人信息，用于个人主页展示
     * 获取用户用户名、签名...
     */
    @Override
    public R userOfId(Integer id) {
        QueryWrapper<Consumer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        return R.success(null, consumerMapper.selectList(queryWrapper));
    }


    /**
     * TODO 前台页面调用  登录
     * 登录判断
     */
    @Override
    public R loginStatus(ConsumerRequest loginRequest, HttpSession session) {

        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        if (this.verityPasswd(username, password)) {

            //浏览器关闭之后，服务器不会销毁session对象
            //为的是在关闭页面后，依然保持登录状态
            session.setAttribute("username", username);
            Consumer consumer = new Consumer();

            //返回表中username是传入的username的那一行数据
            consumer.setUsername(username);
            return R.success("登录成功", consumerMapper.selectList(new QueryWrapper<>(consumer)));
        } else {
            return R.error("用户名或密码错误");
        }
    }

    /**
     * TODO 管理员端页面调用  展示视图
     * 返回每个地区用户有多少人
     */
    @Override
    public R regionOfUser() {
        QueryWrapper<Consumer> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("location","COUNT(*) as count").ne("location","").groupBy("location");
        return R.success(null, consumerMapper.selectMaps(queryWrapper));
    }
}
