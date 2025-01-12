package WordCountProject.src;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;


public class WordCount{

    // Mapper类
    public static class WordMapper extends Mapper<Object, Text, Text, IntWritable>{

        private final static IntWritable one = new IntWritable(1); // 常量值 1
        private Text word = new Text(); // 用于存储每个单词
        
        @Override
        protected void map(Object key, Text value, Context context) throws IOException, InterruptedException{
            // 将每行文本分割成单词
            String[] words = value.toString().split("\\s+");
            for(String w : words){
                word.set(w); // 将单词存储为 Text 类型
                context.write(word, one); // 输出键值对
            }
        }
    }

    // Reducer类
    // 负责将相同的单词分组，并类加其出现次数
    public static class WordReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
        private IntWritable result = new IntWritable();

        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int sum = 0;
            // 遍历所有值
            for(IntWritable val : values){
                sum += val.get();
            }
            result.set(sum);
            context.write(key, result);
        }
    }

    // main
    // 配置并运行 Job
    public static void main(String[] args) throws Exception {
        if(args.length != 2){
            System.out.println("Usage: WordCount <input path> <output path>");
            System.exit(-1);
        }
        
        // 创建 Hadoop 配置
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "Word Count");

        job.setJarByClass(WordCount.class); // 设置主类
        job.setMapperClass(WordMapper.class); // 设置 Mapper 类
        job.setReducerClass(WordReducer.class); // 设置 Reducer 类

        job.setOutputKeyClass(Text.class); // 设置输出键的类型
        job.setOutputValueClass(IntWritable.class); // 设置输出值的类型

        FileInputFormat.addInputPath(job, new Path(args[0])); // 输入路径
        FileOutputFormat.setOutputPath(job, new Path(args[1])); // 输出路径

        System.exit(job.waitForCompletion(true) ? 0 : 1); // 提交 Job
    }
}