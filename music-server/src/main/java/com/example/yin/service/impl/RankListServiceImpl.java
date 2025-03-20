package com.example.yin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.yin.common.R;
import com.example.yin.mapper.RankListMapper;
import com.example.yin.model.domain.RankList;
import com.example.yin.model.request.RankListRequest;
import com.example.yin.service.RankListService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户对歌单的评价计算
 */
@Service
public class RankListServiceImpl extends ServiceImpl<RankListMapper, RankList> implements RankListService {


    @Autowired
    private RankListMapper rankMapper;

    /**
     * TODO 用户提交评分
     */
    @Override
    public R addRank(RankListRequest rankListAddRequest) {
        RankList rankList = new RankList();
        BeanUtils.copyProperties(rankListAddRequest, rankList);
        if (rankMapper.insert(rankList) > 0) {
            return R.success("评价成功");
        } else {
            return R.error("评价失败");
        }
    }

    /**
     * TODO 计算歌单总评分（歌单评分）
     */
    @Override
    public R rankOfSongListId(Long songListId) {
        QueryWrapper<RankList> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("song_list_id",songListId);

        //查询评价歌单人数，若为0，则评分返回0，否则返回计算出的结果
        Long rankNum = rankMapper.selectCount(queryWrapper);
        return R.success(null, (rankNum <= 0) ? 0 : rankMapper.selectScoreSum(songListId) / rankNum);
    }

    /**
     * TODO 某一用户对歌单评分（已评价）
     */
    @Override
    public R getUserRank(Long consumerId, Long songListId) {
        return R.success(null, rankMapper.selectUserRank(consumerId, songListId));
    }
}
