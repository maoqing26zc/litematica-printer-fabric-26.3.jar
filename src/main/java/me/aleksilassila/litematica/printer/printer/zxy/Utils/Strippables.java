//#if MC > 260200
package me.aleksilassila.litematica.printer.printer.zxy.Utils;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 26.3 起 MC 移除了 AxeItem（连同 Fabric API 的 AxeItemAccessor），
 * 原版的去皮映射不再暴露。这里按原版 "stripped_&lt;name&gt;" 命名约定，
 * 从方块注册表重建「原木 -&gt; 去皮原木」映射，结果与 26.3 原版打印机内
 * 硬编码的 BlockHelper.STRIPPED_BLOCKS 一致（含 26.3 新增树种）。
 */
public final class Strippables {
    private static final String PREFIX = "stripped_";
    private static volatile Map<Block, Block> cache;

    private Strippables() {
    }

    public static Map<Block, Block> get() {
        Map<Block, Block> local = cache;
        if (local == null) {
            synchronized (Strippables.class) {
                local = cache;
                if (local == null) {
                    local = build();
                    cache = local;
                }
            }
        }
        return local;
    }

    private static Map<Block, Block> build() {
        Map<Block, Block> map = new LinkedHashMap<>();
        for (Block stripped : BuiltInRegistries.BLOCK) {
            Identifier strippedId = BuiltInRegistries.BLOCK.getKey(stripped);
            if (strippedId == null) {
                continue;
            }
            String path = strippedId.getPath();
            if (!path.startsWith(PREFIX)) {
                continue;
            }
            Identifier sourceId = Identifier.fromNamespaceAndPath(
                    strippedId.getNamespace(), path.substring(PREFIX.length()));
            if (!BuiltInRegistries.BLOCK.containsKey(sourceId)) {
                continue;
            }
            map.put(BuiltInRegistries.BLOCK.getValue(sourceId), stripped);
        }
        return map;
    }
}
//#endif
