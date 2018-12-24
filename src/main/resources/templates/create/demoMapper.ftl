<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.yemh.xqsrd.menu.mapper.IX${demoNameUp}Mapper">
    <!-- 插入 -->
    <insert id="add">
        insert into x_${demoNameLower} (
            <#list tableRows?keys as key>
            ${key}
            </#list>
            gmt_create,
            gmt_modified
        ) values (
            <#list tableRows?keys as key>
            ${'#{'}${tableRows[key]}${'}'},
            </#list>
            ${'#{'}gmtCreate,jdbcType=TIMESTAMP${'}'},
            ${'#{'}gmtModified,jdbcType=TIMESTAMP${'}'}
        )
    </insert>
    <!-- 更新 -->
    <update id="upd">
        update x_${demoNameLower}
        <set>
            <#list tableRows?keys as key>
            <if test="${tableRows[key]} != null or ${tableRows[key]} !=''">${key} = ${'#{'}${tableRows[key]}${'}'},</if>
            </#list>
            <if test="gmt_modified != null or gmt_modified !=''">gmt_modified = ${'#{'}gmtModified${'}'},</if>
        </set>
        <where>
            xid = ${'#{'}xId${'}'}
        </where>
    
    </update>
    <!-- 分页查询 -->
    <select id="getList" resultType="java.util.LinkedHashMap">
        select 
        t1.xid 'xId',
        <#list tableRows?keys as key>
        t1.${key} '${tableRows[key]}',
        </#list>
        t1.gmt_modified 'gmtModified'
        from x_${demoNameLower} t1
        order by t1.gmt_modified
    </select>
    
</mapper>