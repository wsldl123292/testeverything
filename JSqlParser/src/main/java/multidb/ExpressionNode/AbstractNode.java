package multidb.ExpressionNode;

import com.alibaba.fastjson.JSONObject;

public abstract class AbstractNode {
    public enum EXESTATUS {
        /**
         * 失败
         */
        EXE_ERROR,
        /**
         * 成功
         */
        EXE_SUCCESS,
        /**
         * 超时
         */
        EXE_TIMEOUT,
        /**
         * 等待
         */
        EXE_PENDING
    }

    /**
     * 父节点
     */
    AbstractNode parent;

    /**
     * 总数
     */
    long total;

    /**
     * 查询花费时间
     */
    long took;
    /**
     * 构造函数
     *
     * @param parent 父节点
     */
    public AbstractNode(AbstractNode parent) {
        this.parent = parent;
    }

    /**
     * 空构造函数
     */
    public AbstractNode() {

    }

    /**
     * 执行方法
     */
    public void exec() {

    }

    /**
     * 按条返回
     * @return 一条记录
     */
    public JSONObject getNextTuple() {
        return null;
    }

    /**
     * 是否完成
     * @return 完成标识
     */
    public boolean isComplete() {
        return false;
    }

    /**
     * 执行状态
     * @return 状态
     */
    public EXESTATUS getStatus() {
        return EXESTATUS.EXE_PENDING;
    }

    /**
     * 关闭
     */
    public void close() {

    }

    /**
     * 通知
     * @param node 要通知的节点
     */
    public void notify(AbstractNode node) {

    }

    /**
     * 返回数据总数
     *
     * @return total
     */
    public long getTotal() {
        return total;
    }

    /**
     * 返回查询耗时
     *
     * @return took
     */
    public long getTook() {
        return took;
    }
}
