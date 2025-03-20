package com.example.yin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.yin.common.R;
import com.example.yin.mapper.CommentMapper;
import com.example.yin.model.domain.Comment;
import com.example.yin.model.domain.Space;
import com.example.yin.model.request.CommentRequest;
import com.example.yin.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    //提交评论
    @Override
    public R addComment(CommentRequest addCommentRequest) {
        Comment comment = new Comment();
        //将被复制的对象中所有与复制对象中的相同字段给赋值
        BeanUtils.copyProperties(addCommentRequest, comment);

        //因为comment中为type，而addCommentRequest中为nowType，所以需要手动赋值
        comment.setType(addCommentRequest.getNowType());

        //将评论添加到数据库当中
        if (commentMapper.insert(comment) > 0) {
            return R.success("评论成功");
        } else {
            return R.error("评论失败");
        }
    }

    //更新点赞数
    @Override
    public R updateCommentMsg(CommentRequest addCommentRequest) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(addCommentRequest, comment);

        //根据主键id来更新comment表中点赞的值
        if (commentMapper.updateById(comment) > 0) {
            return R.success("点赞成功");
        } else {
            return R.error("点赞失败");
        }
    }

    //删除评论,由前端发送给后端id，在数据库表中删除
    @Override
    public R deleteComment(Integer id) {
        if (commentMapper.deleteById(id) > 0) {
            return R.success("删除成功");
        } else {
            return R.error("删除失败");
        }
    }

    //获取该歌曲下面的所有评论
    @Override
    public R commentOfSongId(Integer songId) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();

        //在表comment中根据song_id进行查找所有评论
        queryWrapper.eq("song_id",songId);
        return R.success(null, commentMapper.selectList(queryWrapper));
    }

    //获取该歌单下面的所有评论
    @Override
    public R commentOfSongListId(Integer songListId) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("song_list_id",songListId);
        return R.success(null, commentMapper.selectList(queryWrapper));
    }

    //展示某个动态中的所有评论
    @Override
    public R commentOfSpaceId(Integer spaceId) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("space_id",spaceId);
        return R.success(null,commentMapper.selectList(queryWrapper));
    }
}
