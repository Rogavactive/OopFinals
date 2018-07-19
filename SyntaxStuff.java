import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import jdk.management.resource.internal.inst.FileOutputStreamRMHooks;

import javax.sql.DataSource;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SyntaxStuff {

    public static void main(String [] args){
        //Sockets
        //Server Side
        try{
            ServerSocket ss = new ServerSocket(9090);//port is an argument and localhost is a host;
            Socket s1 = ss.accept();//This is locked and waits until client tries to connect.
            //getting input
            InputStream s1is = s1.getInputStream();//input byte stream
            InputStreamReader s1isrdr = new InputStreamReader(s1is);//stream cast to string
            BufferedReader s1br = new BufferedReader(s1isrdr);//buffered string. awaits \n on readline.
            String response = s1br.readLine();//this is blocked until \n and returns null if stream is stopped.
            //sending info
            OutputStream oss1 = s1.getOutputStream();//output byte stream
            OutputStreamWriter oss1w = new OutputStreamWriter(oss1);//String writer into stream
            oss1w.write("some text");//sending info
            //Client Side
            Socket s2 = new Socket("localhost",9090);//url and port
            //basically gatting input and sending info is exatly the same as above.
        }catch (IOException e){
            e.printStackTrace();
        }


        /////////////////////////////////////

        //Threads
        class myClass extends Thread{//class should extend Thread
            @Override
            public void run(){
                //this is executed when start() is called on this class
                //doSomethingOnStart();
            }
        }

        myClass myClassinstance = new myClass();
        myClassinstance.start();//thread has been started
        try {
            myClassinstance.wait();//blocking execution of this class on this line, until someone awakes it.
            myClassinstance.notify();//awaking one thread which is wait() ing
            myClassinstance.notifyAll();//awaking all threads which are wait() ing
            synchronized (myClassinstance){
                //all blocks that are synchronized on the same class instance are executed one after another.
                //this blocks all the same blocks in other threads until code gets out of this block
            }
            //locks
            ReentrantLock lock = new ReentrantLock();//thread lock object
            lock.lock();//locking thread. if someone else has called this already executing stops untils someone unlocks
            lock.unlock();//awakes one thread which is locked
            Condition cond = lock.newCondition();//condition for this lock
            cond.await();//locking thread on this condition
            cond.signal();//awaking one thread on this condition
            cond.signalAll();//awaking all threads on this condition
            //CountDownLatch, Semaphore and CyclicBarrier
            CountDownLatch cdl = new CountDownLatch(5);//Takes int and when this int reaches 0 awakes all awaiting processes
            cdl.await();//blocks thread until latch's int reaches 0
            cdl.countDown();//reduces latch's int by 1
            CyclicBarrier cb = new CyclicBarrier(4);//Similar to CountDownLatch, just its Cyclic.
            //after reaching 0 its again set to starting int.
            cb.await();//reduces barriers int and locks thread. if its already 0 releases all waiting threads and sets again to starting int
            Semaphore sem = new Semaphore(4);//if int is reduced to 0, acquire() is blocked until release is called
            sem.acquire();//reduces sem's int
            sem.release();//increaces sem's int
            //join
            myClassinstance.join();//awaiting for this thread to end
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }


        /////////////////////////

        //ajax with jquery
//        $.ajax({
//                url:"ServletMapping",
//                type:'POST' or 'GET',
//                data:{                                //parameter which we take with request.getParameter("parameterKey");
//                    parameteKey:parameterValue,
//                    otherParameterKey:otherParameterValue
//                },
//                success: function(data){
//                    //do something with returned data string
//                }
//        });

        //servlet side:
        //String parameterValue = (String) request.getParameter("parameterKey");
        //do something with taken parameters
        //response.getWriter().write(response_string);






        ///////////////////////////////////////////////////////

        //JDBC driver
        try {
            Class.forName("com.mysql.jdbc.Driver");//loading class

            String url = "jdbc:mysql://localhost:3306/databaseName?useSSL=false";
            //connection pool
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setUser("root");
            dataSource.setPassword("password");
            dataSource.setUrl(url);//3306 is mysql port
            Connection cc = dataSource.getConnection();

            //getting ordinary connection
            Connection conn = DriverManager.getConnection(url,"root","password");
            PreparedStatement ps = conn.prepareStatement("select a.username as user from accounts as a" +
                    "where a.email=?");
            ps.setString(1,"droga16@freeuni.edu.ge");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                String field1 = rs.getString(1);
                String fieldUsername = rs.getString("user");
            }
            ps.executeUpdate();//table updates
            conn.close();//dont forget to close connection
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }



        /////////////

        //

    }

}
