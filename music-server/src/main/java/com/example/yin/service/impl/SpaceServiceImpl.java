package com.example.yin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.yin.common.R;
import com.example.yin.mapper.SpaceMapper;
import com.example.yin.model.domain.Comment;
import com.example.yin.model.domain.Space;
import com.example.yin.model.request.SpaceRequest;
import com.example.yin.service.SpaceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class SpaceServiceImpl extends ServiceImpl<SpaceMapper, Space> implements SpaceService {
    @Autowired
    private SpaceMapper spaceMapper;

    // 添加动态
    @Override
    public R addSpace(SpaceRequest addSpaceRequest) {

        if(addSpaceRequest.getContent() == null)
        {
            return R.error("动态字数不能为0");
        }

        Space space = new Space();
        BeanUtils.copyProperties(addSpaceRequest, space);
        //将评论添加到数据库当中
        if (spaceMapper.insert(space) > 0) {
            return R.success("动态发布成功");
        } else {
            return R.error("动态发布失败");
        }
    }

    //更新动态的点赞数
    @Override
    public R updateSpaceMsg(SpaceRequest upSpaceRequest) {
        Space space = new Space();
        BeanUtils.copyProperties(upSpaceRequest, space);

        //根据主键id来更新comment表中点赞的值
        if (spaceMapper.updateById(space) > 0) {
            return R.success("点赞成功");
        } else {
            return R.error("点赞失败");
        }
    }

    //删除动态
    @Override
    public R deleteSpace(Integer id) {
        if (spaceMapper.deleteById(id) > 0) {
            return R.success("删除动态成功");
        } else {
            return R.error("删除动态失败");
        }
    }

    //展示所有动态
    @Override
    public R allSpace() {
        QueryWrapper<Space> queryWrapper = new QueryWrapper<>();

        return R.success(null,spaceMapper.selectList(queryWrapper));
    }

    @Override
    public R updateSpacePic(MultipartFile spaceFile) {
        String fileName = System.currentTimeMillis() + spaceFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
                + System.getProperty("file.separator") + "spacePic";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }

        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String imgPath = "/img/spacePic/" + fileName;
        try {
            spaceFile.transferTo(dest);
            System.out.println(imgPath);
            return R.success("上传成功", imgPath);
        } catch (IOException e) {
            return R.error("上传失败");
        }
    }


}
