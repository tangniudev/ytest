package MyThread.SupplyAsyc;

import java.util.concurrent.CompletableFuture;

/**
 * Description
 *
 * @author tyw
 * @since 2022/4/3
 * thenCompose连接两个有依赖关系的任务，结构由第二个任务返回
 */
public class Demo_03 {
    public static void main(String[] args) {
        SmallTool.printTimeAndThread("小白进入餐厅");
        SmallTool.printTimeAndThread("小白点了 番茄炒蛋 一份米饭");
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() ->
                {

                    SmallTool.printTimeAndThread("厨师炒菜");
                    SmallTool.sleepMillis(200);
                    return "番茄炒蛋";
                }
        ).thenCompose(dish -> CompletableFuture.supplyAsync(
                () -> {

                    SmallTool.printTimeAndThread("服务员打饭");
                    SmallTool.sleepMillis(200);
                    return dish+ "米饭";
                }
        ));

        SmallTool.printTimeAndThread("小白在打王者");
        SmallTool.printTimeAndThread(String.format("%s ,小白开吃",stringCompletableFuture.join()));
    }
}
