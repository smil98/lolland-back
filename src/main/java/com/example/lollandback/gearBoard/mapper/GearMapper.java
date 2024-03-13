package com.example.lollandback.gearBoard.mapper;

import com.example.lollandback.gearBoard.domain.GearBoard;
import com.example.lollandback.gearBoard.dto.GearBoardDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GearMapper {
    @Insert("""
        INSERT INTO gearboard (gear_title, gear_content, category,member_id)
        VALUES (#{gear_title},#{gear_content},#{category},#{member_id});
    """)
    //  생성되기전에 미리  데이터 값 추가하기
    // 파일이 어떤게시물의 파일인지 알아야해서
    @Options(useGeneratedKeys = true,keyProperty = "gear_id")
    int insert(GearBoard gearBoard);

    @Select("""
        SELECT
          b.gear_id,
          b.gear_title,
          b.gear_content,
          b.category,
          b.gear_inserted,
          b.gear_views,
          COUNT(DISTINCT f.id) AS countFile,
          COUNT(DISTINCT c.id) AS commentcount,
          COUNT(DISTINCT l.id) AS countLike
        FROM
          gearboard b
        LEFT JOIN
          lolland.gearfile f ON b.gear_id = f.gearboard_id
        LEFT JOIN
          lolland.gearcomment c ON b.gear_id = c.boardid
        LEFT JOIN
          lolland.gearlike l ON b.gear_id = l.gearboardId
        WHERE
          b.category = #{category}
        GROUP BY
          b.gear_id,
          b.gear_title,
          b.gear_content,
          b.category,
          b.gear_inserted,
          b.gear_views
        ORDER BY
          b.gear_inserted DESC;
        """)
    List<GearBoard> list(String category);

//        SELECT
//          b.gear_id,
//          b.gear_title,
//          b.gear_content,
//          b.category,
//          b.gear_inserted,
//          b.gear_views,
//          COUNT(DISTINCT f.id) AS countFile,
//          COUNT(DISTINCT c.id) AS commnetcount,
//          COUNT(DISTINCT l.id) AS countLike
//        FROM
//          gearboard b
//        LEFT JOIN
//          lolland.gearfile f ON b.gear_id = f.gearboard_id
//        LEFT JOIN
//          lolland.gearcomment c ON b.gear_id = c.boardid
//        LEFT JOIN
//          lolland.gearlike l ON b.gear_id = l.gearboardId
//        WHERE
//          b.category = #{category}
//        GROUP BY
//          b.gear_id
//        ORDER BY
//             b.gear_inserted desc ;

    @Select("""
             SELECT
                 b.gear_id,
                 b.gear_title,
                 b.gear_content,
                 b.category,
                 b.gear_inserted,
                 b.gear_views,
                 COUNT(DISTINCT f.id) AS countFile,
                 COUNT(DISTINCT c.id) AS commentcount,
                 COUNT(DISTINCT l.id) AS countLike,
                 m.member_introduce,
                 m.member_name,
                 m.id AS member_id,
                 m.member_email,
                 mi.file_name,
                 mi.file_url
             FROM
                 gearboard b
                     LEFT JOIN
                 lolland.gearfile f ON b.gear_id = f.gearboard_id
                     LEFT JOIN
                 lolland.gearcomment c ON b.gear_id = c.boardid
                     LEFT JOIN
                 lolland.gearlike l ON b.gear_id = l.gearboardId
                     JOIN
                 member m ON b.member_id = m.member_login_id
                     LEFT JOIN
                 memberimage mi ON m.id = mi.member_id
             WHERE b.gear_id=#{gear_id}
             GROUP BY
                 b.gear_id;
        """)
    GearBoardDto getId(Integer gear_id);

    @Delete("""
                delete from gearboard where gear_id=#{gear_id};
        """)

    int remove(Integer gear_id);

    @Update("""
                update gearboard set  category=#{category} ,
                  gear_content=#{gear_content},
                  gear_title=#{gear_title}  
                  where gear_id=#{gear_id};
        """)
    int saveup(GearBoard gearBoard);


    @Select("""
SELECT
    b.gear_id,
    b.gear_title,
    b.gear_content,
    b.category,
    b.gear_inserted,
    b.gear_views,
    COUNT(DISTINCT f.id) AS countFile,
    COUNT(DISTINCT c.id) AS commnetcount,
    COUNT(DISTINCT l.id) AS countLike
FROM
    gearboard b
        LEFT JOIN
    lolland.gearfile f ON b.gear_id = f.gearboard_id
        LEFT JOIN
    lolland.gearcomment c ON b.gear_id = c.boardid
        LEFT JOIN
    lolland.gearlike l ON b.gear_id = l.gearboardId
where b.gear_id=#{gear_id}
GROUP BY
    b.gear_id
        """)

    GearBoard selectById(Integer gear_id);

    @Select("""    
SELECT
    b.gear_id,
    b.gear_title,
    b.gear_content,
    b.category,
    b.gear_inserted,
    b.gear_views,
    COUNT(DISTINCT f.id) AS countFile,
    COUNT(DISTINCT c.id) AS commentcount,
    COUNT(DISTINCT l.id) AS countLike
FROM
    gearboard b
        LEFT JOIN
    lolland.gearfile f ON b.gear_id = f.gearboard_id
        LEFT JOIN
    lolland.gearcomment c ON b.gear_id = c.boardid
        LEFT JOIN
    lolland.gearlike l ON b.gear_id = l.gearboardId
-- WHERE
--         b.category = #{category}
GROUP BY
    b.gear_id
ORDER BY
    b.gear_inserted desc 
    limit  #{from},10
            """)
    List<GearBoard> listAll(Integer from);

    @Select("""
        SELECT
    b.gear_id,
    b.gear_title,
    b.gear_content,
    b.category,
    b.gear_inserted,
    b.gear_views,
    COUNT(DISTINCT f.id) AS countFile,
    COUNT(DISTINCT c.id) AS commentcount,
    COUNT(DISTINCT l.id) AS countLike
FROM
    gearboard b
        LEFT JOIN
    lolland.gearfile f ON b.gear_id = f.gearboard_id
        LEFT JOIN
    lolland.gearcomment c ON b.gear_id = c.boardid
        LEFT JOIN
    lolland.gearlike l ON b.gear_id = l.gearboardId
-- WHERE
--         b.category = #{category}
GROUP BY
    b.gear_id
ORDER BY
    countLike desc 
LIMIT 5;
        """)
    List<GearBoard> listss();


    @Select("""
        SELECT
    b.gear_id,
    b.gear_title,
    b.gear_content,
    b.category,
    b.gear_inserted,
    b.gear_views,
    COUNT(DISTINCT f.id) AS countFile,
    COUNT(DISTINCT c.id) AS commentcount,
    COUNT(DISTINCT l.id) AS countLike,
    f.file_url as mainfile
FROM
    gearboard b
        LEFT JOIN
    lolland.gearfile f ON b.gear_id = f.gearboard_id
        LEFT JOIN
    lolland.gearcomment c ON b.gear_id = c.boardid
        LEFT JOIN
    lolland.gearlike l ON b.gear_id = l.gearboardId
-- WHERE
--         b.category = #{category}
GROUP BY
    b.gear_id
ORDER BY
    b.gear_inserted desc    
LIMIT 10;

        """)
    List<GearBoard> listto();

    @Select("""
select count(*)
from gearboard;
            """)
    int countAll();
}

