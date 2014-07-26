package threadTest;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

//import org.apache.log4j.Logger;

/**
 * http://www.blogjava.net/standlww/archive/2008/10/17/235100.html
 * 
* 线程池
* 创建线程池，销毁线程池，添加新任务
* 
* @author obullxl
*/
public final class ThreadPool {
//    private static Logger logger = Logger.getLogger(ThreadPool.class);
//    private static Logger taskLogger = Logger.getLogger("TaskLogger");
//
//    private static boolean debug = taskLogger.isDebugEnabled();
    // private static boolean debug = taskLogger.isInfoEnabled();
    /* 单例 */
    private static ThreadPool instance = ThreadPool.getInstance();

    public static final int SYSTEM_BUSY_TASK_COUNT = 150;
    /* 默认池中线程数 */
    public static int worker_num = 5;
    /* 已经处理的任务数 */
    private static int taskCounter = 0;

    public static boolean systemIsBusy = false;

    private static List<Task> taskQueue = Collections
            .synchronizedList(new LinkedList<Task>());
    /* 池中的所有线程 */
    public PoolWorker[] workers;

    private ThreadPool() {
        workers = new PoolWorker[5];
        for (int i = 0; i < workers.length; i++) {
            workers[i] = new PoolWorker(i);
        }
    }

    private ThreadPool(int pool_worker_num) {
        worker_num = pool_worker_num;
        workers = new PoolWorker[worker_num];
        for (int i = 0; i < workers.length; i++) {
            workers[i] = new PoolWorker(i);
        }
    }

    public static synchronized ThreadPool getInstance() {
        if (instance == null)
            return new ThreadPool();
        return instance;
    }
    /**
    * 增加新的任务
    * 每增加一个新任务，都要唤醒任务队列
    * @param newTask
    */
    public void addTask(Task newTask) {
        synchronized (taskQueue) {
            newTask.setTaskId(++taskCounter);
            newTask.setSubmitTime(new Date());
            taskQueue.add(newTask);
            /* 唤醒队列, 开始执行 */
            taskQueue.notifyAll();
        }
//        logger.info("Submit Task<" + newTask.getTaskId() + ">: "
//                + newTask.info());
    }
    /**
    * 批量增加新任务
    * @param taskes
    */
    public void batchAddTask(Task[] taskes) {
        if (taskes == null || taskes.length == 0) {
            return;
        }
        synchronized (taskQueue) {
            for (int i = 0; i < taskes.length; i++) {
                if (taskes[i] == null) {
                    continue;
                }
                taskes[i].setTaskId(++taskCounter);
                taskes[i].setSubmitTime(new Date());
                taskQueue.add(taskes[i]);
            }
            /* 唤醒队列, 开始执行 */
            taskQueue.notifyAll();
        }
        for (int i = 0; i < taskes.length; i++) {
            if (taskes[i] == null) {
                continue;
            }
//            logger.info("Submit Task<" + taskes[i].getTaskId() + ">: "
//                    + taskes[i].info());
        }
    }
    /**
    * 线程池信息
    * @return
    */
    public String getInfo() {
        StringBuffer sb = new StringBuffer();
        sb.append("\nTask Queue Size:" + taskQueue.size());
        for (int i = 0; i < workers.length; i++) {
            sb.append("\nWorker " + i + " is "
                    + ((workers[i].isWaiting()) ? "Waiting." : "Running."));
        }
        return sb.toString();
    }
    /**
    * 销毁线程池
    */
    public synchronized void destroy() {
        for (int i = 0; i < worker_num; i++) {
            workers[i].stopWorker();
            workers[i] = null;
        }
        taskQueue.clear();
    }

    /**
    * 池中工作线程
    * 
    * @author obullxl
    */
    private class PoolWorker extends Thread {
        private int index = -1;
        /* 该工作线程是否有效 */
        private boolean isRunning = true;
        /* 该工作线程是否可以执行新任务 */
        private boolean isWaiting = true;

        public PoolWorker(int index) {
            this.index = index;
            start();
        }

        public void stopWorker() {
            this.isRunning = false;
        }

        public boolean isWaiting() {
            return this.isWaiting;
        }
        /**
        * 循环执行任务
        * 这也许是线程池的关键所在
        */
        public void run() {
            while (isRunning) {
                Task r = null;
                synchronized (taskQueue) {
                    while (taskQueue.isEmpty()) {
                        try {
                            /* 任务队列为空，则等待有新任务加入从而被唤醒 */
                            taskQueue.wait(20);
                        } catch (InterruptedException ie) {
//                            logger.error(ie);
                        }
                    }
                    /* 取出任务执行 */
                    r = (Task) taskQueue.remove(0);
                }
                if (r != null) {
                    isWaiting = false;
                    try {
//                        if (debug) {
                            r.setBeginExceuteTime(new Date());
//                            taskLogger.debug("Worker<" + index
//                                    + "> start execute Task<" + r.getTaskId() + ">");
//                            if (r.getBeginExceuteTime().getTime()
//                                    - r.getSubmitTime().getTime() > 1000)
//                                taskLogger.debug("longer waiting time. "
//                                        + r.info() + ",<" + index + ">,time:"
//                                        + (r.getFinishTime().getTime() - r
//                                                .getBeginExceuteTime().getTime()));
//                        }
                        /* 该任务是否需要立即执行 */
                        if (r.needExecuteImmediate()) {
                            new Thread(r).start();
                        } else {
                            r.run();
                        }
//                        if (debug) {
                            r.setFinishTime(new Date());
//                            taskLogger.debug("Worker<" + index
//                                    + "> finish task<" + r.getTaskId() + ">");
//                            if (r.getFinishTime().getTime()
//                                    - r.getBeginExceuteTime().getTime() > 1000)
//                                taskLogger.debug("longer execution time. "
//                                        + r.info() + ",<" + index + ">,time:"
//                                        + (r.getFinishTime().getTime() - r
//                                                .getBeginExceuteTime().getTime()));
//                        }
                    } catch (Exception e) {
                        e.printStackTrace();
//                        logger.error(e);
                    }
                    isWaiting = true;
                    r = null;
                }
            }
        }
    }
}

/** 任务接口类 **/
/**
* 所有任务接口
* 其他任务必须继承访类
* 
* @author obullxl
*/
abstract class Task implements Runnable {
    // private static Logger logger = Logger.getLogger(Task.class);
    /* 产生时间 */
    private Date generateTime = null;
    /* 提交执行时间 */
    private Date submitTime = null;
    /* 开始执行时间 */
    private Date beginExceuteTime = null;
    /* 执行完成时间 */
    private Date finishTime = null;

    private long taskId;

    public Task() {
        this.generateTime = new Date();
    }

    /**
    * 任务执行入口
    */
    public void run() {
        /**
        * 相关执行代码
        * 
        * beginTransaction();
        * 
        * 执行过程中可能产生新的任务 subtask = taskCore();
        * 
        * commitTransaction();
        * 
        * 增加新产生的任务 ThreadPool.getInstance().batchAddTask(taskCore());
        */
    }

    /**
    * 所有任务的核心 所以特别的业务逻辑执行之处
    * 
    * @throws Exception
    */
    public abstract Task[] taskCore() throws Exception;

    /**
    * 是否用到数据库
    * 
    * @return
    */
    protected abstract boolean useDb();

    /**
    * 是否需要立即执行
    * 
    * @return
    */
    protected abstract boolean needExecuteImmediate();

    /**
    * 任务信息
    * 
    * @return String
    */
    public abstract String info();

    public Date getGenerateTime() {
        return generateTime;
    }

    public Date getBeginExceuteTime() {
        return beginExceuteTime;
    }

    public void setBeginExceuteTime(Date beginExceuteTime) {
        this.beginExceuteTime = beginExceuteTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

}
