package com.example.yin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.yin.common.R;
import com.example.yin.model.domain.UserSupport;
import com.example.yin.model.request.UserSupportRequest;


public interface UserSupportService extends IService<UserSupport> {

    R isUserSupportComment(UserSupportRequest userSupportRequest);

    R insertCommentSupport(UserSupportRequest userSupportRequest);

    R deleteCommentSupport(UserSupportRequest userSupportRequest);

    R isUserSupportSpace(UserSupportRequest userSupportRequest);

    R insertSpaceSupport(UserSupportRequest userSupportRequest);

    R deleteSpaceSupport(UserSupportRequest userSupportRequest);
}
