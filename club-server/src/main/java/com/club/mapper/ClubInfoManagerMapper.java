package com.club.mapper;

import com.club.dto.ClubInfoManagerDTO;
import com.club.vo.ActivityVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClubInfoManagerMapper {
    /***
     * 更新社团的部分主要信息
     * @param clubInfoManagerDTO
     */
    void updateBaseClubInfo(ClubInfoManagerDTO clubInfoManagerDTO);

    /***
     * 删除membership表中club_id和role为1的数据
     * @param clubId
     */
    @Delete("delete from membership where club_id = #{clubId} and role = 1")
    void deleteClubLeader(Integer clubId);

    /***
     * 添加membership表中club_id和role为1的会长数据
     * @param clubInfoManagerDTO
     */
    void addClubLeader(ClubInfoManagerDTO clubInfoManagerDTO);

    /***
     * 删除club_photo_urls表中该club_id的数据
     * @param clubId
     */
    @Delete("delete from club_photo_urls where club_id = #{clubId}")
    void deleteClubPhotos(Integer clubId);

    /***
     * 添加club_photo_urls表中club_id的照片url数据
     * @param clubId
     * @param photoUrls
     */
    void addClubPhotos(Integer clubId, List<String> photoUrls);

    /***
     * 获取社团的活动的信息
     * @param clubId
     * @return
     */
    @Select("select activity_id,title from activity where club_id = #{clubId}")
    List<ActivityVO> getActivityByClubId(Integer clubId);
}
