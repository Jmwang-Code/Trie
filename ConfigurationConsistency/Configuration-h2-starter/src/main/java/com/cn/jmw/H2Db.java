package com.cn.jmw;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author jmw
 * @Description 管理器 -> 解析器 -> 优化器 -> 过期判断器 -> 执行器
 * @date 2022年11月26日 17:01
 * @Version 1.0
 *
 *      Parser -> Analysis of non-relational database,First you need an intermediate,
 * Such as "DATAFRAME".Finally parse the "DATAFRAME" into a "ResultSet" object.
 * Parser only focus on parsing.
 *
 *      Actuator -> Open the connection between persistent and db files,For example,
 * adding,deleting,modifying,checking,table control statement.
 * Executors only focus on executing
 *
 *      Manager(Provider) -> Provide external API and manage persistence.
 *
 *      Expired judge -> First you need to maintain an expiration table,
 * periodically check expiration fields.The API interface needs to provide an absolute IP.
 *
 *      Optimizer -> Original library, write library, read library, enable optimization,
 * split the original library into writing library and reading library.
 * Timing synchronization (simple timing synchronization and execution forced synchronization)
 *
 */
public class H2Db {

    private static final String MEM_URL = "jdbc:h2:mem:/";

    private static final String LOCAL_URL = "jdbc:h2:file:";

    private static final String H2_PARAM = ";LOG=0;DATABASE_TO_UPPER=false;MODE=MySQL;CASE_INSENSITIVE_IDENTIFIERS=TRUE;CACHE_SIZE=65536;LOCK_MODE=0;UNDO_LOG=0";

    /**
     * @Description Persistent connection
     * @Author jmw
     * @Date 18:07 2022/11/26
     */
    public void PersistentConnection(){
        String url =LOCAL_URL+"C:\\Users\\jmw\\Desktop\\jmw-code\\Trie\\ConfigurationConsistency\\Configuration-h2-starter\\src\\main\\resources"+"/test"+H2_PARAM;
        try (Connection connection = DriverManager.getConnection(url, "jmw", "123456");){
            connection.prepareStatement("CREATE TABLE IF NOT EXISTS `cache_expire` ( `source_id` VARCHAR(128),`expire_time` DATETIME )").execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
    }
}
