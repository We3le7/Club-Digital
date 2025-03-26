package com.club.service.impl;

import com.club.dto.ClubInfoManagerDTO;
import com.club.dto.ClubInfoUserDTO;
import com.club.mapper.ClubInfoManagerMapper;
import com.club.mapper.ConfigMapper;
import com.club.service.ClubManagerService;
import com.club.vo.ActivityVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.List;

@Service
@Slf4j
public class ClubManagerServiceImpl implements ClubManagerService{
    @Autowired
    private ClubInfoManagerMapper clubInfoManagerMapper;

    @Autowired
    private ConfigMapper configMapper;
    /***
     * 修改社团相关信息
     * @param clubInfoManagerDTO
     */
    @Override
    public void updateClubInfo(ClubInfoManagerDTO clubInfoManagerDTO) {
        //club_id不可以修改

        //首先获取config_info表中的内容
        String clubTypeData = configMapper.getConfig("社团类型");

        //需要将String转换为对应的JSON格式
        try {
            // 将JSON数据转换为JSONObject
            JSONObject jsonObject = new JSONObject(clubTypeData);

            // 遍历JSONObject的键值对
            // 获取对应的社团类型Id
            Iterator<String> keys = jsonObject.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                String value = jsonObject.getString(key);
                if(value.equals(clubInfoManagerDTO.getClubType())){
                    clubInfoManagerDTO.setClubTypeId(Integer.parseInt(key));
                    break;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //获取校区类型
        String campusTypeData = configMapper.getConfig("校区类别");

        //需要将String转换为对应的JSON格式
        try {
            // 将JSON数据转换为JSONObject
            JSONObject jsonObject = new JSONObject(campusTypeData);

            // 遍历JSONObject的键值对
            // 获取对应的校区类型Id
            Iterator<String> keys = jsonObject.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                String value = jsonObject.getString(key);
                if(value.equals(clubInfoManagerDTO.getDistrict())){
                    clubInfoManagerDTO.setDistrictId(Integer.parseInt(key));
                    break;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //修改社团的基本信息
        //社团类型和校区类型只可以修改为数据库中已有的
        clubInfoManagerMapper.updateBaseClubInfo(clubInfoManagerDTO);

        //先删除membership表中club_id和role为1的行数据
        clubInfoManagerMapper.deleteClubLeader(clubInfoManagerDTO.getClubId());

        //增加新的会长信息
        clubInfoManagerMapper.addClubLeader(clubInfoManagerDTO);

        //删除这个club_id中的所有照片url
        clubInfoManagerMapper.deleteClubPhotos(clubInfoManagerDTO.getClubId());
        
        //添加这个club_id的照片url
        List<String> photoUrls = clubInfoManagerDTO.getClubPhotoUrls();
        clubInfoManagerMapper.addClubPhotos(clubInfoManagerDTO.getClubId(),photoUrls);
    }

    /***
     * 获取社团的活动的信息
     * @param clubId
     * @return
     */
    @Override
    public List<ActivityVO> getActivityByClubId(Integer clubId) {
        //直接通过mapper查询即可
        List<ActivityVO> activityVOList = clubInfoManagerMapper.getActivityByClubId(clubId);
        return activityVOList;
    }
}
