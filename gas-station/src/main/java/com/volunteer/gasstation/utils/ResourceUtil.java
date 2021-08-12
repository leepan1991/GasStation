package com.volunteer.gasstation.utils;

import com.volunteer.gasstation.manager.system.dto.ResourceDTO;
import com.volunteer.gasstation.manager.system.entity.Resource;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author by huoyao on 2021/8/12.
 */
public class ResourceUtil {

    public final static List<ResourceDTO> loop(List<? extends ResourceDTO> resourceList) {
        List<ResourceDTO> list = resourceList.stream().filter(m -> m.getParentId() == null).map(
                (m) -> {
                    m.setChildren(getChildren(m, resourceList));
                    return m;
                }
        ).collect(Collectors.toList());
        return list;
    }

    /**
     * 递归查询子节点
     * @param root  根节点
     * @param all   所有节点
     * @return 根节点信息
     */
    private static List<ResourceDTO> getChildren(ResourceDTO root, List<? extends ResourceDTO> all) {
        List<ResourceDTO> children = all.stream().filter(m -> Objects.equals(m.getParentId(), root.getId())).map(
                (m) -> {
                    m.setChildren(getChildren(m, all));
                    return m;
                }
        ).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(children)) {
            return null;
        }
        return children;
    }
}
