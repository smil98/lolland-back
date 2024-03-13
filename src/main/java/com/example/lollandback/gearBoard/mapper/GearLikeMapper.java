package com.example.lollandback.gearBoard.mapper;

import com.example.lollandback.gearBoard.domain.GearLike;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface GearLikeMapper {
    @Select("""
            SELECT count(id)
            FROM gearlike
            WHERE gearboardId = #{gearboardId};
        """)
    int countByBoardId(Integer gear_id);

    @Select("""
            SELECT * FROM gearlike
            WHERE gearboardId = #{gear_id} AND memberId = #{id};
        """)
    GearLike selectbyId(Integer gear_id, Long id);

    @Insert("""
            INSERT INTO gearlike (gearboardId, memberId)
            VALUES (#{gearboardId},#{memberId});
        """)
    int insert(GearLike gearLike);

    @Delete("""
            DELETE FROM gearlike
            WHERE gearboardId = #{gearboardId} AND memberId = #{memberId};
        """)
    int delete(GearLike gearLike);

    @Delete("""
        DELETE FROM gearlike
        WHERE gearboardId = #{gearboardId}
    """)
    int deleteAllLikeByBoardId(Integer gear_id);
}
