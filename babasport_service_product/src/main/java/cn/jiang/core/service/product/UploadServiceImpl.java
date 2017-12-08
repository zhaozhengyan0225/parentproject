package cn.jiang.core.service.product;

import org.springframework.stereotype.Service;

import cn.jiang.common.fdfs.FastDFSUtils;

@Service("uploadService")
public class UploadServiceImpl implements UploadService{
	//上传图片
	public String uploadPic(byte[] pic ,String name,long size){
		return FastDFSUtils.uploadPic(pic, name, size);
	}
}
