
import java.util.List;
import java.util.Map;

/**
 * @author yemh
 * @date 2018/12/24
 */
public interface IX${demoNameUp}Mapper {

    int add(Map<String, Object> params);

    int upd(Map<String, Object> params);

    List<Map<String, Object>> getList(Map<String, Object> params);
}
