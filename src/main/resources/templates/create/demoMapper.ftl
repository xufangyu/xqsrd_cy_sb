<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.yemh.xqsrd.menu.mapper.IXMenuMapper">

    <insert id="add${demoNameUp}">
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
        ${'#{'}gmtModified,jdbcType=TIMESTAMP${'}'},
        )
    </insert>
    
    <update id="updMenu">
        update x_menu
        <set>
        <if test="menuName != null or menuName !=''">menu_name = ${'#{'}menuName},</if>
        <if test="menuPath != null or menuPath !=''">menu_path = ${'#{'}menuPath},</if>
        <if test="menuComponent != null or menuComponent !=''">menu_component = ${'#{'}menuComponent},</if>
        <if test="menuIcon != null or menuIcon !=''">menu_icon = ${'#{'}menuIcon},</if>
        <if test="parentId != null or parentId !=''">parent_id = ${'#{'}parentId},</if>
        <if test="leaf != null or leaf !=''">is_leaf = ${'#{'}leaf},</if>
        <if test="pos != null or pos !=''">pos = ${'#{'}pos},</if>
        <if test="gmt_modified != null or gmt_modified !=''">gmt_modified = ${'#{'}gmtModified},</if>
        </set>
        <where>
        xid = ${'#{'}xId}
        </where>
    
    </update>
    <!-- 查询，根据参数获取菜单,用于菜单管理界面 -->
    <select id="getList" resultType="java.util.LinkedHashMap">
        select 
        t1.xid 'xId',
        t1.menu_name 'menuName',
        t1.menu_path 'menuPath',
        t1.menu_component 'menuComponent',
        t1.menu_icon 'icon',
        t2.menu_name 'parentName',
        t2.xid 'parentId',
        t1.is_leaf 'leaf',
        t1.pos 'pos'
        from x_menu t1
        left join x_menu t2
        on t1.parent_id = t2.xid
        order by t1.pos;
    </select>
    
</mapper>