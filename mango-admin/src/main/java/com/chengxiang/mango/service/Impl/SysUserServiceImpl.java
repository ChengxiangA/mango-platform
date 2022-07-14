package com.chengxiang.mango.service.Impl;

import com.chengxiang.mango.common.utils.PoiUtil;
import com.chengxiang.mango.common.utils.ReflectionUtil;
import com.chengxiang.mango.constant.SysConstants;
import com.chengxiang.mango.entity.SysMenu;
import com.chengxiang.mango.entity.SysUser;
import com.chengxiang.mango.mapper.SysMenuMapper;
import com.chengxiang.mango.mapper.SysUserMapper;
import com.chengxiang.mango.page.MybatisPageHelper;
import com.chengxiang.mango.page.PageRequest;
import com.chengxiang.mango.page.PageResult;
import com.chengxiang.mango.service.SysMenuService;
import com.chengxiang.mango.service.SysUserService;
import com.chengxiang.mango.util.DateUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysMenuMapper sysMenuMapper;

    @Override
    public int update(SysUser sysUser) {
        return sysUserMapper.updateByPrimaryKeySelective(sysUser);
    }

    @Override
    public int save(SysUser record) {
        return sysUserMapper.insert(record);
    }

    @Override
    public int delete(SysUser record) {
        return sysUserMapper.deleteByPrimaryKey(record.getId());
    }

    /**
     * 批量删除，但是要排除超级管理员
     * @param records
     * @return
     */
    @Override
    public int delete(List<SysUser> records) {
        for (SysUser record : records) {
            SysUser sysUser = findById(record.getId());
            if(sysUser != null && SysConstants.ADMIN.equals(sysUser.getName())) {
                return 0;
            }
            delete(record);
        }
        return 1;
    }


    @Override
    public SysUser findById(Long id) {
        return sysUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SysUser> findAll() {
        return sysUserMapper.findAll();
    }

    /**
     * 分页查询
     * @param pageRequest
     * @return
     */
    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest,sysUserMapper);
    }

    @Override
    public SysUser findByName(String name) {
        return sysUserMapper.findByName(name);
    }

    @Override
    public void createUserExcelFile(PageRequest pageRequest,HttpServletResponse response) throws IOException {
        PageResult page = findPage(pageRequest);
        createUserExcelFile(page.getContent(),response);
    }

    @Override
    public Set<String> findPermissions(String username) {
        Set<String> perms = new HashSet<>();
        List<SysMenu> menus = sysMenuMapper.findByUsername(username);
        for (SysMenu menu : menus) {
            if(menu.getPerms() != null && menu.getPerms().length() != 0) {
                perms.add(menu.getPerms());
            }
        }
        return perms;
    }

    public static void createUserExcelFile(List<SysUser> records, HttpServletResponse response) throws IOException {
        if(records == null) {
            records = new ArrayList<>();
        }
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();
        XSSFRow row0 = sheet.createRow(0);
        int columnIndex = 0;
        row0.createCell(columnIndex).setCellValue("NO");
        row0.createCell(++ columnIndex).setCellValue("ID");
        row0.createCell(++ columnIndex).setCellValue("用户名");
        row0.createCell(++ columnIndex).setCellValue("昵称");
        row0.createCell(++ columnIndex).setCellValue("机构");
        row0.createCell(++ columnIndex).setCellValue("角色");
        row0.createCell(++ columnIndex).setCellValue("邮箱");
        row0.createCell(++ columnIndex).setCellValue("手机号");
        row0.createCell(++ columnIndex).setCellValue("状态");
        row0.createCell(++ columnIndex).setCellValue("头像");
        row0.createCell(++ columnIndex).setCellValue("创建人");
        row0.createCell(++ columnIndex).setCellValue("创建时间");
        row0.createCell(++ columnIndex).setCellValue("最后更新人");
        row0.createCell(++ columnIndex).setCellValue("最后更新时间");
        for(int i = 1;i <= records.size();i ++) {
            columnIndex = 0;
            XSSFRow row = sheet.createRow(i);
            SysUser sysUser = records.get(i - 1);
            row.createCell(columnIndex).setCellValue(i);
            row.createCell(++ columnIndex).setCellValue(sysUser.getId());
            row.createCell(++ columnIndex).setCellValue(sysUser.getName());
            row.createCell(++ columnIndex).setCellValue(sysUser.getNickName());
            row.createCell(++ columnIndex).setCellValue(sysUser.getDeptName());
            row.createCell(++ columnIndex).setCellValue(sysUser.getRoleNames());
            row.createCell(++ columnIndex).setCellValue(sysUser.getEmail());
            row.createCell(++ columnIndex).setCellValue(sysUser.getMobile());
            row.createCell(++ columnIndex).setCellValue(sysUser.getStatus());
            row.createCell(++ columnIndex).setCellValue(sysUser.getAvatar());
            row.createCell(++ columnIndex).setCellValue(sysUser.getCreateBy());
            row.createCell(++ columnIndex).setCellValue(sysUser.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss")));
            row.createCell(++ columnIndex).setCellValue(sysUser.getLastUpdateBy());
            row.createCell(++ columnIndex).setCellValue(sysUser.getLastUpdateTime().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss")));
        }
        ServletOutputStream out = response.getOutputStream();
        response.setContentType("application/octet-stream;charset=utf-8");
        response.setHeader("Content-Disposition","attachment;filename=" + new String(SysConstants.DOWNLOAD_FILENAME.getBytes(),"utf-8") );
        response.addHeader("Pargam", "no-cache");
        response.addHeader("Cache-Control", "no-cache");
        workbook.write(out);
        out.close();
    }
}
