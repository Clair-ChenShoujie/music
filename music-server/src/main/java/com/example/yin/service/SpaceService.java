package com.example.yin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.yin.common.R;
import com.example.yin.model.domain.Space;
import com.example.yin.model.request.SpaceRequest;
import org.springframework.web.multipart.MultipartFile;


public interface SpaceService {

    R addSpace(SpaceRequest addSpaceRequest);

    R updateSpaceMsg(SpaceRequest upSpaceRequest);

    R deleteSpace(Integer id);

    R allSpace();

    R updateSpacePic(MultipartFile File);

}
