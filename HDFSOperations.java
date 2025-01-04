import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;

import java.io.*;
import java.nio.file.FileSystem;
import java.nio.file.Path;

import javax.naming.ConfigurationException;

public class HDFSOperations {
    
    public static void main(String[] args) throws IOException {
        // 1. 配置 HDFS客户端
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://localhost:9000"); // 设置 namenode地址
        FileSystem fs = FileSystem.get(conf); // FileSystem 类属于 Apache Hadoop 项目的 org.apache.hadoop.fs 包

        // 2. 创建目录
        Path dirPath = new Path("/user/sscc/mydir");
        if(!fs.exists(dirPath)){
            fs.mkdirs(dirPath);
            System.out.println("Directory created: " + dirPath);
        }

        // 3. 上传文件到 HDFS
        Path localFilePath = new Path("/Users/sscc/Desktop/bigdata/Hadoop/hello.java"); // 本地文件路径
        Path hdfsFilePath = new Path("/user/sscc/mydir/remote_hello.java");
        // hdfsFilePath中，如果明确指定目标文件名（如 /user/sscc/mydir/romote_hello.java），文件会被复制到 HDFS 并以该名称存储。
	    // 这种灵活性允许在 HDFS 中对文件进行重命名或组织，而不一定需要保留原始的本地文件名
        fs.copyFromLocalFile(localFilePath, hdfsFilePath);
        System.out.println("File uploaded to: " + hdfsFilePath);

        // 4. 移动文件
        Path newHdfsPath = new Path("/user/sscc/mydir/moved_hello.java");
        if(fs.rename(hdfsFilePath, newHdfsPath)){
            System.out.println("File moved to: " + newHdfsPath);
        }

        // 5. 读取文件内容
        System.out.println("File content:");
        try(FSDataInputStream inputStream = fs.open(newHdfsPath)){
            IOUtils.coppyBytes(inputStream, System.out, 4096, false);
            // 从 HDFS读取的数据通过 inputStream 复制到 System.out，即标准输出（控制台）
            // 这里的 4096 是缓冲区的大小(4KB)，指定每次读取的字节数。这个值有助于优化性能，避免每次读取数据时都进行 I/O操作
            // false 表示不关闭 inputStream，因为我们在 try-with-resources 中已经指定了自动关闭
        }

        // 6. 下载文件到本地
        Path downloadedFilePath = new Path("downloaded_hello.java");
        fs.copyToLocalFile(newHdfsPath, downloadedFilePath);
        System.out.println("File downloaded to: " + downloadedFilePath);

        // 7. 删除文件
        if(fs.exists(newHdfsPath)){
            fs.delete(newHdfsPath, false);
            System.out.println("File deleted: " + newHdfsPath);
        }

        // 8. 列出目录内容
        System.out.println("Listing directory content:");
        FileStatus[] fileStatuses = fs.listStatus(dirPath);
        for(FileStatus status : fileStatuses){
            System.out.println(status.getPath().toString());
        }
        // FileStatus 用来描述文件或目录的状态信息
        // FileStatus 类是 FileSystem 类的一部分。
        // 位于 Hadoop 的 org.apache.hadoop.fs 包中
        // 通过 FileSystem 类的一些方法（如 listStatus()），可以获取到一个或多个 FileStatus 对象，进而了解文件或目录的各种属性
        /*
        FileSystem fs = FileSystem.get(conf);
        Path dirPath = new Path("/user/hadoop");
        FileStatus[] fileStatuses = fs.listStatus(dirPath);

        for (FileStatus fileStatus : fileStatuses) {
            System.out.println("Path: " + fileStatus.getPath());
            System.out.println("Is directory: " + fileStatus.isDirectory());
            System.out.println("Size: " + fileStatus.getLen() + " bytes");
            System.out.println("Owner: " + fileStatus.getOwner());
            System.out.println("Group: " + fileStatus.getGroup());
            System.out.println("Modification time: " + fileStatus.getModificationTime());
        }
        */

        // 9. 删除目录
        if(fs.exists(dirPath)){
            fs.delete(dirPath, true); // true 表示递归删除
            System.out.println("Directory deleted: " + dirPath);
        }

        // 10. 关闭文件系统
        fs.close();
    }

    
}