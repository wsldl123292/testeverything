package multidb.ExpressionNode;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.saibowisdom.base.api.multidb.AbstractNode;
import com.saibowisdom.base.api.multidb.QueryCallback;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;


public class LocalFileResultNode extends AbstractNode {

    /**
     * 执行节点
     */
    AbstractNode child;
    /**
     * 回调
     */
    QueryCallback callback;
    /**
     * 临时文件
     */
    File ftemp = null;
    /**
     * 状态
     */
    EXESTATUS exestatus = EXESTATUS.EXE_PENDING;
    /**
     * 使换行符转为字节数组
     */
    String lineSeparator = System.getProperty("line.separator");
    /**
     * 换行
     */
    byte[] newLine = lineSeparator.getBytes();
    /**
     * 线程池
     */
    // public ThreadPoolExecutor execute = new ThreadPoolExecutor(2, 7, 1800L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

    /**
     * 构造函数
     *
     * @param node     执行节点
     * @param callback 回调函数
     */
    public LocalFileResultNode(final AbstractNode node, final QueryCallback callback) {
        this.child = node;
        this.callback = callback;

        child.exec();
        try {
            ftemp = File.createTempFile("selectresult", ".json");
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        final Thread thread = new Thread(new Runnable() {
            public void run() {
                runTask();
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    /**
     * 构造函数
     *
     * @param node 执行节点
     */
    public LocalFileResultNode(AbstractNode node) {
        this(node, null);
    }

    @Override
    public void exec() {
        child.exec();
        try {
            ftemp = File.createTempFile("selectresult", ".json");
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        //execute.execute(new ThreadPoolTask(this));
    }

    @Override
    public JSONObject getNextTuple() {
        final JSONObject obj = new JSONObject();
        if (ftemp != null) {
            obj.put("filename", ftemp.getAbsolutePath());
            return obj;
        }
        return null;
    }

    @Override
    public boolean isComplete() {
        // TODO Auto-generated method stub
        return super.isComplete();
    }

    @Override
    public EXESTATUS getStatus() {

        return exestatus;
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub
        child.close();
    }

    @Override
    public void notify(AbstractNode node) {
        // TODO Auto-generated method stub
        super.notify(node);
    }

    /**
     * 执行函数
     */
    public void runTask() {
        // 处理一个任务，这里的处理方式太简单了，仅仅是一个打印语句
        System.out.println(Thread.currentThread().getName());
        FileOutputStream writeFile = null;
        try {
            if (ftemp != null) {
                writeFile = new FileOutputStream(ftemp);
            }
        } catch (FileNotFoundException e1) {
            exestatus = EXESTATUS.EXE_ERROR;

        }

        //child.getNextTuple();
        String json;
        while (true) {
                /*JSONObject obj = child.getNextTuple();
                if (obj == null || obj.isEmpty())
                    break;
                json = obj.toJSONString();

                try {
                    if (writeFile != null) {
                        writeFile.write(json.getBytes("UTF-8"));
                        // writeFile.write(json.toString().getBytes("UTF-8"));

                    }*/
            final Map<String, Object> obj = child.getNextTuple();
            if (obj == null || obj.isEmpty()) {
                break;
            }
            json = JSON.toJSONString(obj);

            try {
                if (writeFile != null) {
                    writeFile.write(json.getBytes("UTF-8"));
                    writeFile.write(newLine);
                    // writeFile.write(json.toString().getBytes("UTF-8"));

                }
            } catch (IOException e1) {
                exestatus = EXESTATUS.EXE_ERROR;
                //e1.printStackTrace();
            }
        }

        try {
            if (writeFile != null) {
                writeFile.close();
            }
        } catch (IOException e) {
            exestatus = EXESTATUS.EXE_ERROR;
            //e.printStackTrace();
        }
        exestatus = EXESTATUS.EXE_SUCCESS;
        if (callback != null) {
            callback.callback(ftemp != null ? ftemp.getAbsolutePath() : null, exestatus);
        }
        //execute.shutdown();
    }



    /*class ThreadPoolTask implements Runnable, Serializable {
        private static final long serialVersionUID = 0;

        *//** 保存任务所需要的数据 *//*
        private AbstractNode scanNode;
        *//**
     * 构造函数
     * @param node 执行节点
     *//*
        ThreadPoolTask(AbstractNode node) {
            this.scanNode = node;
        }
        *//**
     * 执行函数
     *//*
        public void run() {
            // 处理一个任务，这里的处理方式太简单了，仅仅是一个打印语句
            System.out.println(Thread.currentThread().getName());
            FileOutputStream writeFile = null;
            try {
                if (ftemp != null) {
                    writeFile = new FileOutputStream(ftemp);
                }
            } catch (FileNotFoundException e1) {
                exestatus = EXESTATUS.EXE_ERROR;

            }

            //child.getNextTuple();
            String json;
            while (true) {
                *//*JSONObject obj = child.getNextTuple();
                if (obj == null || obj.isEmpty())
                    break;
                json = obj.toJSONString();

                try {
                    if (writeFile != null) {
                        writeFile.write(json.getBytes("UTF-8"));
                        // writeFile.write(json.toString().getBytes("UTF-8"));

                    }*//*
                final Map<String, Object> obj = child.getNextTuple();
                if (obj == null || obj.isEmpty()) {
                    break;
                }
                json = JSON.toJSONString(obj);

                try {
                    if (writeFile != null) {
                        writeFile.write(json.getBytes("UTF-8"));
                        writeFile.write(newLine);
                        // writeFile.write(json.toString().getBytes("UTF-8"));

                    }
                } catch (IOException e1) {
                    exestatus = EXESTATUS.EXE_ERROR;
                    //e1.printStackTrace();
                }
            }

            try {
                if (writeFile != null) {
                    writeFile.close();
                }
            } catch (IOException e) {
                exestatus = EXESTATUS.EXE_ERROR;
                //e.printStackTrace();
            }
            exestatus = EXESTATUS.EXE_SUCCESS;
            if (callback != null) {
                //callback.callback(scanNode, exestatus);
            }
            //execute.shutdown();
        }

    }*/
}
