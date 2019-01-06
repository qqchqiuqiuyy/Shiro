package cn.bb.springbootshiro.mapper;

import cn.bb.springbootshiro.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from user where NAME = #{name}")
    public User findByName(@Param("name") String name);

    @Select("select * from user where id = #{id}")
    public User findById(@Param("id") Integer id);
}
