
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageRowBounds;
import com.yemh.xqsrd.base.AbstractBaseService;
import com.yemh.xqsrd.base.util.DateTimeUtil;
import com.yemh.xqsrd.base.util.StringUtil;

/**
 * @author yemh
 * @date 2018/12/24
 */
@Service("${demoNameUp}Service")
public class ${demoNameUp}ServiceImpl extends AbstractBaseService implements ${demoNameUp}Service {

    @Autowired
    private IX${demoNameUp}Mapper iXMapper;
    

    /* 
     * 
     */
    @Override
    public String add(Map<String, Object> params) {
        String dateTime = DateTimeUtil.getSystemDate("yyyy-MM-dd HH:mm:ss");
        params.put("gmtCreate", dateTime);
        params.put("gmtModified", dateTime);
        
        int res;
        try {
            res = iXMapper.add(params);
            if (res > 0) {
                return S("添加成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return F("添加失败");
    }

    /* 
     * 
     */
    @Override
    public String upd(Map<String, Object> params) {
        String dateTime = DateTimeUtil.getSystemDate("yyyy-MM-dd HH:mm:ss");
        // params.put("gmtCreate", dateTime);
        params.put("gmtModified", dateTime);

        int res;
        try {
            res = iXMapper.upd(params);
            if (res > 0) {
                return S("更新成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return F("更新失败");
    }

    @Override
    public String getList(Map<String, Object> params) {

        int pageNum = 0, pageSize = 0;
        String page = (String)params.get("page");
        if (StringUtil.isEmpty(page)) {
            pageNum = 1;
        } else {
            pageNum = Integer.parseInt(page);
        }
        String limit = (String)params.get("limit");
        if (StringUtil.isEmpty(limit)) {
            pageSize = 20;
        } else {
            pageSize = Integer.parseInt(limit);
        }

        Page<Map<String, Object>> pageList = null;
        try {
            pageList = iXMapper.getList(new PageRowBounds(pageNum, pageSize), params);
            if(pageList != null) {
                return S("获取所有成功", pageList, pageList.getTotal());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return F("获取失败");
    }

}
