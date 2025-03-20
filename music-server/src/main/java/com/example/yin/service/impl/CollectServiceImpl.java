package com.example.yin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.yin.common.R;
import com.example.yin.mapper.CollectMapper;
import com.example.yin.model.domain.Collect;
import com.example.yin.model.request.CollectRequest;
import com.example.yin.service.CollectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollectServiceImpl extends ServiceImpl<CollectMapper, Collect> implements CollectService {
    @Autowired
    private CollectMapper collectMapper;

    //添加收藏歌曲
    @Override
    public R addCollection(CollectRequest addCollectRequest) {
        Collect collect = new Collect();

        //因为collectMapper查询返回的类型是collect，所以需要将CollectRequest转变为Collect类型
//        因为前端传入的数据类型和后端使用数据库进行查询的数据类型不一样，所以要进行赋值转化
        BeanUtils.copyProperties(addCollectRequest, collect);
        if (collectMapper.insert(collect) > 0) {
            return R.success("收藏成功", true);
        } else {
            return R.error("收藏失败");
        }
    }

    //查询某个歌曲是否已经被收藏
    @Override
    public R existSongId(CollectRequest isCollectRequest) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id",isCollectRequest.getUserId());
        queryWrapper.eq("song_id",isCollectRequest.getSongId());
        if (collectMapper.selectCount(queryWrapper) > 0) {
            return R.success("已收藏", true);
        } else {
            return R.success("未收藏", false);
        }
    }

    //取消收藏歌曲
    @Override
    public R deleteCollect(Integer userId, Integer songId) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("song_id",songId);

        //先查询该歌曲是否被收藏，如果被收藏了就在表collect中删除
        if (collectMapper.delete(queryWrapper) > 0) {
            return R.success("取消收藏", false);
        } else {
            return R.error("取消收藏失败");
        }
    }

    @Override
    public R collectionOfUser(Integer userId) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id",userId);
        return R.success("用户收藏", collectMapper.selectList(queryWrapper));
    }

    @Override
    public R collectionOfSort() {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper();
        queryWrapper.select("song_id","COUNT(*) as count").groupBy("song_id").orderByDesc("count");
        return R.success("歌曲排行榜", collectMapper.selectMaps(queryWrapper));
    }
}
