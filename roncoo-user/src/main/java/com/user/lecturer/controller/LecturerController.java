package com.user.lecturer.controller;

import com.model.dto.Dto;
import com.model.dto.DtoUtil;
import com.model.generator.pojo.*;
import com.model.vo.CreateLecturerVo;
import com.model.vo.SelectLecturerVo;
import com.user.lecturer.service.LecturerAuditService;
import com.user.lecturer.service.LecturerService;
import com.utils.EmptyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@Api(value = "LecturerController", description = "讲师接口测试")
public class LecturerController {


    @Autowired
    LecturerService lecturerService;

    @Autowired
    LecturerAuditService lecturerAuditService;

    @GetMapping("/select/lecturer")
    @ApiOperation(value = "查询讲师数据", httpMethod = "GET", produces = "application/json", response = Dto.class, protocols = "HTTP",
            notes = "<p>系统出现异常：10002</p>")
    public Dto selectLecturer(SelectLecturerVo vo) {
        try {
            LecturerExample example = new LecturerExample();
            LecturerExample.Criteria criteria = example.createCriteria();
            if (EmptyUtils.isNotEmpty(vo.getStatusId()))
                criteria.andStatusIdEqualTo(vo.getStatusId());
            if (EmptyUtils.isNotEmpty(vo.getLecturer_name()))
                criteria.andLecturerNameEqualTo(vo.getLecturer_name());
            if (EmptyUtils.isNotEmpty(vo.getLecturerMobile()))
                criteria.andLecturerMobileEqualTo(vo.getLecturerMobile());

            List<Lecturer> lecturers = lecturerService.lecturers(example);
            if (EmptyUtils.isEmpty(lecturers)) {
                return DtoUtil.returnSuccess("未查询到对应的讲师信息");
            }
            return DtoUtil.returnDataSuccess(lecturers);
        } catch (Exception e) {
            return DtoUtil.returnFail("系统出现异常", "10002");
        }
    }

    @PostMapping(value = "/create/lecturer", consumes = "multipart/*", headers = "content-type=multipart/form-data")
    @ApiOperation(value = "新增讲师数据")
    @ResponseBody
    public Dto createLecturer(@RequestParam("LecturerEmail") String LecturerEmail,
                              @RequestParam("StatusId") Byte StatusId,
                              @RequestParam("LecturerMobile") String LecturerMobile,
                              @RequestParam("LecturerUserNo") long LecturerUserNo,
                              @RequestParam("LecturerName") String LecturerName,
                              @RequestParam("Sort") Integer Sort,
                              @RequestParam("Introduce") String Introduce,
                              @RequestParam("LecturerProportion") BigDecimal LecturerProportion,
                              @ApiParam(value = "上传图片", required = true) MultipartFile file) {
        try {
            String temp = "images" + File.separator + "upload" + File.separator;
            // 获取图片的文件名
            String fileName = file.getOriginalFilename();
            // 获取图片的扩展名
            String extensionName = fileName.substring(fileName.indexOf("."));
            // 新的图片文件名 = 获取时间戳+"."图片扩展名
            String newFileName = String.valueOf(System.currentTimeMillis()) + "." + extensionName;
            // 数据库保存的目录
            String datdDirectory = temp.concat(String.valueOf(1)).concat(File.separator);
            // 文件路径
            String filePath = "D:\\" + datdDirectory;

            File dest = new File(filePath, newFileName);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            // 上传到指定目录
            file.transferTo(dest);

            LecturerAudit audit = new LecturerAudit();
            audit.setHeadImgUrl(filePath + "\\" + newFileName);
            audit.setLecturerEmail(LecturerEmail);
            audit.setStatusId(StatusId);
            audit.setLecturerMobile(LecturerMobile);
            audit.setLecturerName(LecturerName);
            audit.setLecturerUserNo(LecturerUserNo);
            audit.setSort(Sort);
            audit.setIntroduce(Introduce);
            audit.setLecturerProportion(LecturerProportion);
            audit.setGmtCreate(new Date());
            audit.setGmtModified(new Date());
            audit.setAuditStatus((byte) 0);//审核状态默认为0

            int result = lecturerAuditService.insert(audit);

            if (result == 0)
                return DtoUtil.returnFail("系统出现未知异常", "10002");
            return DtoUtil.returnSuccess("新增成功");
        } catch (Exception e) {
            return DtoUtil.returnFail("系统出现异常", "10002");
        }

    }

    @ApiOperation(value = "查询单个讲师数据", httpMethod = "GET")
    @GetMapping("select/lecturer/{id}")
    public Dto getLecturer(@PathVariable("id") long id) {
        Lecturer lecturer = lecturerService.getLecturerById(id);
        if (EmptyUtils.isEmpty(lecturer))
            return DtoUtil.returnFail("不存在当前讲师", "10002");
        return DtoUtil.returnDataSuccess(lecturer);
    }

    @ApiOperation(value = "查询单个讲师账户余额数据", httpMethod = "GET")
    @GetMapping("select/lecturer/Balance/{id}")
    public Dto getLecturerByBalance(@PathVariable("id") long id) {
        LecturerExt lecturerExt = lecturerService.getLecturerExtById(id);
        if (EmptyUtils.isEmpty(lecturerExt))
            return DtoUtil.returnFail("不存在当前讲师", "10002");
        return DtoUtil.returnDataSuccess(lecturerExt);
    }


    @ApiOperation("图片上传测试接口")
    @PostMapping(value = "/upload", consumes = "multipart/*", headers = "content-type=multipart/form-data")
    public Dto upload(@ApiParam(value = "上传图片", required = true) MultipartFile file) {
        if (!file.isEmpty()) {
            if (file.getContentType().contains("image")) {
                try {
                    String temp = "images" + File.separator + "upload" + File.separator;
                    // 获取图片的文件名
                    String fileName = file.getOriginalFilename();
                    // 获取图片的扩展名
                    String extensionName = fileName.substring(fileName.indexOf("."));
                    // 新的图片文件名 = 获取时间戳+"."图片扩展名
                    String newFileName = String.valueOf(System.currentTimeMillis()) + "." + extensionName;
                    // 数据库保存的目录
                    String datdDirectory = temp.concat(String.valueOf(1)).concat(File.separator);
                    // 文件路径
                    String filePath = "D:\\" + datdDirectory;

                    File dest = new File(filePath, newFileName);
                    if (!dest.getParentFile().exists()) {
                        dest.getParentFile().mkdirs();
                    }
                    // 上传到指定目录
                    file.transferTo(dest);
                    return DtoUtil.returnSuccess("上传成功");
                } catch (Exception e) {
                    return DtoUtil.returnFail("上传失败", "10000");
                }
            }
        }
        return DtoUtil.returnFail("文件null", "10000");
    }
}
