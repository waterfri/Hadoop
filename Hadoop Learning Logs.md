#  Hadoop Learning Logs

started from 2024.12.27



**学习时间分配建议**



**阶段**	**内容**	**时间建议**

基础知识	分布式计算与 Hadoop 入门	2 天 

核心组件	HDFS、MapReduce、YARN	5-7 天

Hadoop 生态系统	Hive、HBase、Sqoop 等工具学习	5 天

高级优化与安全	集群优化、容错与监控	3 天

实践项目	实现至少 1 个综合性小项目	3-5 天

## What is Hadoop?

**什么是 Hadoop？**

Hadoop 是一个**开源的大数据处理框架**，旨在存储和处理大规模的数据集。它基于**分布式存储**和**分布式计算**的设计理念，能在普通硬件上以高效、可靠的方式处理海量数据。

**Hadoop 的核心组成**

Hadoop 包括三个主要核心组件：

​	1.	**HDFS（Hadoop Distributed File System）**：分布式文件存储系统。

​	2.	**MapReduce**：分布式计算框架。

​	3.	**YARN（Yet Another Resource Negotiator）**：资源管理和任务调度框架。

此外，Hadoop 还拥有丰富的生态系统，支持诸如数据分析、实时计算、消息处理等功能。



**Hadoop 核心组成详解**

**1. HDFS：分布式文件存储**

​	•	**作用**：用于存储大规模数据，具备高容错性和高吞吐量。

​	•	**结构**：

​	•	**NameNode（主节点）**：管理元数据（文件目录结构、权限、块位置）。

​	•	**DataNode（数据节点）**：存储实际数据块。

​	•	**关键特性**：

​	•	数据块存储：文件被切分为多个块（默认 128MB），分布在不同节点。

​	•	副本机制：每个数据块存储多个副本（默认 3 个）以保证数据可靠性。

​	•	**工作流程**：

​	•	读文件：客户端向 NameNode 请求文件位置，随后直接从 DataNode 获取数据。

​	•	写文件：客户端将数据分块，依次写入多个 DataNode，同时 NameNode 更新元数据。



**2. MapReduce：分布式计算引擎**

​	•	**作用**：负责处理存储在 HDFS 中的数据，采用分布式计算模型。

​	•	**结构**：

​	•	**JobTracker**（YARN 中由 ResourceManager 替代）：负责任务调度。

​	•	**TaskTracker**（YARN 中由 NodeManager 替代）：负责执行具体任务。

​	•	**计算流程**：

​	•	**Map 阶段**：

​	•	将输入数据切分为键值对（key-value）形式。

​	•	每个键值对经过用户自定义的 Mapper 函数处理，生成中间结果。

​	•	**Shuffle 阶段**：

​	•	对中间结果进行排序和分组，确保同一键的所有值被传递给同一个 Reducer。

​	•	**Reduce 阶段**：

​	•	接收分组后的数据，执行用户定义的 Reducer 函数，输出最终结果。



**3. YARN：资源管理与任务调度**

​	•	**作用**：负责集群资源的统一管理和调度，是 MapReduce 的升级版。

​	•	**结构**：

​	•	**ResourceManager（RM）**：集群级别的资源管理器。

​	•	调度器（Scheduler）：为应用程序分配资源。

​	•	应用管理器（ApplicationManager）：启动任务。

​	•	**NodeManager（NM）**：节点级别的资源代理，负责向 ResourceManager 汇报状态并执行任务。

​	•	**ApplicationMaster**：为每个应用程序分配资源和管理生命周期。

​	•	**工作原理**：

​	1.	客户端提交应用程序（例如 MapReduce 作业）。

​	2.	ResourceManager 分配资源。

​	3.	ApplicationMaster 协调任务执行。

​	4.	NodeManager 运行实际任务。



**Hadoop 的体系结构**

Hadoop 的体系结构分为三个主要层次：

**1. 存储层（HDFS）**

​	•	**作用**：实现分布式存储，提供高可靠性和高吞吐量。

​	•	**特点**：

​	•	数据分布在多个节点上。

​	•	提供副本机制，支持数据冗余存储。

​	•	**主要组件**：NameNode、DataNode。



**2. 计算层（MapReduce 或其他计算框架）**

​	•	**作用**：通过分布式计算模型处理 HDFS 中的数据。

​	•	**特点**：

​	•	将数据处理逻辑分发到多个节点。

​	•	支持任务分片和并行计算。

​	•	**主要组件**：Mapper、Reducer、Shuffle。



**3. 资源管理层（YARN）**

​	•	**作用**：协调存储和计算资源，动态分配任务。

​	•	**特点**：

​	•	资源按需分配，提高资源利用率。

​	•	支持多种计算引擎（如 MapReduce、Spark）。

​	•	**主要组件**：ResourceManager、NodeManager、ApplicationMaster。



**Hadoop 组件之间的关系**

​	1.	**HDFS 是基础**：

​	•	存储大数据集，为计算层提供数据输入。

​	2.	**MapReduce 执行计算**：

​	•	从 HDFS 获取数据，执行分布式计算，计算结果写回 HDFS。

​	3.	**YARN 提供资源支持**：

​	•	管理计算和存储资源，调度 MapReduce 或其他计算任务。



**Hadoop 生态系统**

除了核心组件，Hadoop 还有大量扩展工具，构成一个完整的生态系统：

​	1.	**Hive**：基于 SQL 的数据仓库，支持对 HDFS 中的数据进行查询。

​	2.	**Pig**：脚本化数据处理工具，适合处理复杂的批量任务。

​	3.	**HBase**：NoSQL 数据库，支持随机读写。

​	4.	**Sqoop**：用于在 Hadoop 和关系型数据库之间传输数据。

​	5.	**Zookeeper**：分布式协调服务。

	6.	**Oozie**：工作流调度工具。
	6.	

**Hadoop 的典型工作流程**

以 MapReduce 任务为例：

​	1.	**数据准备**：

​	•	数据通过 Sqoop 或其他工具导入 HDFS。

​	2.	**任务提交**：

​	•	用户编写 MapReduce 作业，提交给 YARN。

​	3.	**任务调度**：

​	•	YARN 的 ResourceManager 分配资源，启动 ApplicationMaster。

​	4.	**任务执行**：

​	•	ApplicationMaster 调度 Task，Task 从 HDFS 读取数据进行处理。

​	5.	**结果输出**：

​	•	处理后的结果存储回 HDFS 或导出到数据库。

**Hadoop 的优势与不足**

**优势**

​	•	高可扩展性：支持横向扩展。

​	•	容错性强：副本机制确保数据安全。

​	•	生态丰富：支持各种场景的扩展工具。

​	•	成本低：基于普通硬件构建分布式系统。

**不足**

​	•	性能：MapReduce 基于磁盘的计算速度较慢。

​	•	实时性差：适合批量处理，不适合低延迟场景。

​	•	学习曲线：需要掌握多个工具和概念。

---

下面是一个系统全面的 **Hadoop 学习框架**，帮助你逐步掌握 Hadoop 的核心知识及其生态系统的相关内容。该框架分为基础、核心组件、生态系统、高级应用和实践五个模块，适合从入门到精通的学习过程。



# **Hadoop 学习框架**



## **1. 基础知识**



### **1.1 分布式计算与存储**

#### 1.1.1 什么是分布式系统？

**分布式系统**是由多个独立计算机（或节点）组成的系统，通过网络协作完成任务。它的核心思想是将复杂任务分解到多个节点上并行处理，最终合并结果，从而提升性能、可靠性和可扩展性。

---

#### 1.1.2 分布式计算与存储的基本原理

**分布式计算**是指将大规模任务分解成多个子任务，分发到多个节点上并行处理。

**特点**：

​	•	**并行处理**：多个计算机同时处理不同部分任务。

​	•	**任务分解与调度**：将任务合理分配给各节点。

​	•	**结果合并**：各节点完成任务后，系统汇总最终结果。

应用场景：

​	•	搜索引擎（如 Google 索引构建）

​	•	科学计算（如基因测序、天气模拟）

​	•	数据分析（如 MapReduce）

**分布式存储**是将数据分散存储到多个节点上，通过多个副本保证数据的高可用性和容错性。

**特点**：

​	•	**数据分块**：数据被分为小块，分布到不同的存储节点上（如 HDFS）。

​	•	**副本机制**：每份数据有多个副本，防止单节点故障。

​	•	**高扩展性**：可以通过增加节点扩展存储容量。

常见分布式存储系统：

​	•	HDFS（Hadoop Distributed File System）

​	•	Ceph（高性能分布式存储系统）

​	•	Amazon S3（云存储服务）

分布式系统通常结合计算和存储，通过将数据存储和计算分布到不同节点上，减少数据传输，提高性能

---

#### 1.1.3 数据一致性与分布式容错（CAP 理论）

**CAP理论**

分布式系统中无法同时满足**一致性（Consistency）**、**可用性（Availability）**和**分区容忍性（Partition Tolerance）**，需要权衡。

---

一致性（Consistency）、可用性（Availability）和分区容忍性（Partition Tolerance）是分布式系统中的核心概念，它们被称为 **CAP 定理** 的三个基本属性。CAP 定理指出，在分布式系统中，这三者无法同时完全满足，只能在其中选择两个进行权衡。以下是它们的具体含义：

**1. 一致性（Consistency）**

一致性指的是**所有节点上的数据在任何时间点都保持一致**。

​	•	当一个节点的更新操作完成后，所有的读操作都能立即获取到最新的数据。

​	•	数据的一致性在用户体验中表现为无论访问哪个节点，都可以获得相同的结果。

**例子**：

​	•	一个银行转账系统：如果用户在一个节点上存入了100元，系统需要保证其他节点也立即知道这个操作，否则可能会造成余额查询错误。

**一致性的代价**：

​	•	一致性通常需要通过同步机制（如分布式锁、事务等）实现，可能会增加系统延迟。



**2. 可用性（Availability）**

可用性指的是**系统在任何时候都可以响应用户的请求**，即使某些节点发生故障，系统也不会拒绝服务。

​	•	系统应保证每个请求都能获得非错误的响应（尽管可能不是最新的数据）。

​	•	强调的是服务的连续性和可用性。

**例子**：

​	•	一个电商网站：即使某些节点宕机，用户仍然可以正常浏览商品和下单，而不会出现“服务不可用”的提示。

**可用性的代价**：

​	•	在某些情况下，为了保证系统的可用性，可能会返回稍微过时的数据（弱一致性）。



**3. 分区容忍性（Partition Tolerance）**

分区容忍性指的是**当分布式系统的某些节点之间的网络通信出现故障时，系统仍然能够继续运行**。

​	•	分区是指网络中的节点因故障被分隔为多个孤立的部分（如网络中断）。

​	•	分区容忍性要求系统在分区期间，仍能保证部分或全部功能的正常运行。

**例子**：

​	•	一个分布式数据库部署在多个数据中心，如果某个数据中心与其他中心失去联系（网络分区），系统仍然需要提供服务，而不是完全停摆。

**分区容忍性的代价**：

​	•	为了应对分区，系统可能会牺牲一致性或可用性。



**CAP 三者的冲突**

CAP 定理指出，在分布式系统中，不可能同时满足以下三点：

​	1.	数据始终一致（Consistency）。

​	2.	系统始终可用（Availability）。

​	3.	系统能够容忍网络分区（Partition Tolerance）。



因此，系统需要根据应用场景在以下两种组合中进行权衡：

​	1.	**CP（一致性 + 分区容忍性）**：

​	•	牺牲可用性，在分区发生时，部分服务可能不可用。

​	•	示例：分布式数据库系统（如 HBase）。

​	2.	**AP（可用性 + 分区容忍性）**：

​	•	牺牲一致性，在分区发生时，可能返回非最新数据（最终一致性）。

​	•	示例：分布式缓存系统（如 Cassandra、DynamoDB）。

**总结**

CAP 定理的核心是帮助开发者理解分布式系统的设计权衡。在具体应用中，需要根据业务需求选择不同的策略：

​	•	如果需要**强一致性**（如金融系统），选择 **CP**。

​	•	如果需要高可用性（如社交网络、日志系统），选择 **AP**。



**为什么网络分区发生时，CAP中只能满足两个**

CAP 定理并不是说分布式系统在正常情况下一定要舍弃一个属性，而是说在**网络分区发生时**，系统必须在一致性和可用性之间做出权衡。

**核心矛盾**在于：当网络分区发生时，节点之间无法通信，要保证一致性，就必须暂停响应；而要保证可用性，就无法确保数据一致。

---

**容错性**

系统在节点或硬件故障时仍能正常工作，通常通过副本机制、心跳检测等实现。

---

#### 1.1.4 4V 特性（Volume, Variety, Velocity, Veracity）

关于分布式系统中的 **4V 特性**，这是描述 **大数据系统** 特点的一种方法。它指的是大数据和分布式系统需要处理的四个主要方面，这些特性通常是设计 Hadoop 等分布式系统的重要出发点。

**4V 特性**

​	1.	**Volume（数据量）**

​	•	**含义**：数据规模巨大，通常以 TB、PB 或更高为单位。

​	•	**挑战**：传统单机无法存储和处理如此大量的数据，因此需要分布式存储（如 HDFS）和分布式计算（如 MapReduce）。

​	•	**应对方式**：采用横向扩展（增加节点）的方法来存储和处理数据。

​	2.	**Velocity（速度）**

​	•	**含义**：数据生成和处理的速度非常快，尤其是实时数据流（如物联网设备、金融交易、社交网络流量）。

​	•	**挑战**：系统需要实时或准实时地处理数据，传统批处理无法满足需求。

​	•	**应对方式**：通过流式计算框架（如 Apache Kafka、Apache Flink）实现低延迟处理。

​	3.	**Variety（多样性）**

​	•	**含义**：数据的类型多样，包括结构化数据（如数据库记录）、非结构化数据（如文本、图片、视频）和半结构化数据（如 JSON、XML）。

​	•	**挑战**：传统数据库系统只能处理结构化数据，而无法灵活支持多种数据格式。

​	•	**应对方式**：通过支持多种数据格式的系统（如 Hadoop 支持 JSON、CSV、Parquet 等格式）进行统一管理。

​	4.	**Veracity（真实性）**

​	•	**含义**：数据的质量、准确性和可靠性。随着数据规模的增大，可能包含噪声、异常值或错误数据。

​	•	**挑战**：如何从海量数据中提取出高质量、有意义的信息。

​	•	**应对方式**：通过数据清洗、数据校验和数据融合等技术，提升数据的可信度和准确性。



**4V 的实际应用**

以电商系统为例：

​	•	**Volume**：每日产生海量用户浏览、下单和支付记录。

​	•	**Velocity**：需要实时处理用户点击行为，推荐商品。

​	•	**Variety**：数据包括用户行为日志（非结构化）、订单记录（结构化）以及用户评价（半结构化）。

​	•	**Veracity**：需要保证订单数据和支付数据的准确性，并剔除无效点击和虚假信息。



### **1.2 Hadoop 简介**

该部分在开头已经提及，故省略

#### 1.2.1 Hadoop 的起源与发展

#### 1.2.2 Hadoop 的核心思想：分布式存储 + 分布式计算

#### 1.2.3 Hadoop 的核心组件（HDFS、MapReduce、YARN）

#### 1.2.4 Hadoop 的生态系统概览



### **1.3 安装与配置**

**环境准备（Java、SSH、Hadoop 文件结构）**

在开始安装和配置 Hadoop 之前，必须做好环境准备工作。

**1. Java 环境**

​	•	Hadoop 依赖 Java 环境，需安装 JDK（推荐 JDK 8 或 11）。

​	•	设置 Java 环境变量，确保 Hadoop 能正确识别。

**2. SSH 环境**

​	•	为了实现集群节点间的通信，需要配置 SSH 无密码登录。

​	•	配置每个节点的 ~/.ssh/authorized_keys 文件，以便通过 SSH 免密访问。

​	•	使用以下命令设置 SSH 配置：

```bash
ssh-keygen -t rsa
ssh-copy-id user@hostname
```

**3. Hadoop 文件结构**：

​	•	**HADOOP_HOME**：Hadoop 安装目录。

​	•	**bin**：存放 Hadoop 二进制文件。

​	•	**etc/hadoop**：存放 Hadoop 配置文件。

​	•	**logs**：存放日志文件。

​	•	**tmp**：存放临时文件。

​	•	**sbin**：存放集群启动和停止的脚本。

#### 1.3.1 单节点模式配置

我使用了单节点模式进行Hadoop的学习

---

`jps` 是一个 Java 进程状态监视工具，它会列出当前运行的 Java 进程，包括 Hadoop 的各个守护进程。通过 jps 命令，你可以检查 Hadoop 是否成功启动。

```jps```

如果 Hadoop 启动成功，你应该看到类似如下的输出：

```bash
12345 NameNode
12346 DataNode
12347 ResourceManager
12348 NodeManager
```

#### 1.3.2 伪分布式模式配置

#### 1.3.3 全分布式模式集群搭建



## **2. 核心组件**



### **2.1 HDFS（Hadoop Distributed File System）**

**HDFS** 是 Hadoop 的 **分布式文件系统**，负责存储大规模数据

**与 MapReduce 和 YARN 的关系**

​	•	**存储 MapReduce 输入和输出数据**：MapReduce 需要依赖 HDFS 提供数据存储功能。

​	•	**共享文件系统**：YARN 和其他分布式计算框架都可以直接使用 HDFS 作为底层存储。

---



#### 2.1.1 HDFS 结构

​	•	NameNode 和 DataNode 的角色及功能

**NameNode** 是 HDFS 中的主节点，负责整个文件系统的元数据管理。它**记录文件和块的映射关系**，以及文件块存储在哪些 DataNode 上。它不会存储实际的数据，而是**只存储元数据**，如文件的目录结构、块的位置信息等。NameNode 是 HDFS 的核心，负责文件的命名、目录结构的维护和副本的管理。

主要功能：

​	•	管理文件系统的元数据。

​	•	跟踪文件块的位置和副本数量。

​	•	负责协调 HDFS 中的数据块的创建、删除和复制。

**DataNode** 是存储文件数据的节点，它负责存储实际的文件块。每个 DataNode 管理着本地磁盘上的一个或多个数据块，并且定期向 NameNode 汇报数据块的健康状态。DataNode 提供数据的读写操作，客户端通过与 DataNode 交互来访问存储的数据。

主要功能：

​	•	存储文件的实际数据块。

​	•	定期向 NameNode 汇报数据块状态。

​	•	执行文件读写请求。

---

​	•	Secondary NameNode 的作用

**Secondary NameNode** 不是 NameNode 的备份，它的主要作用是**辅助** NameNode 进行编辑日志的**合并**和文件系统**镜像**的**创建**。NameNode 每次写入修改时都会记录操作日志，但这些日志会随着时间增长变得非常大。Secondary NameNode 定期合并这些日志，以减少 NameNode 启动时的恢复时间。需要注意的是，Secondary NameNode 并不能完全替代 NameNode，它的作用更多是帮助降低 NameNode 恢复的复杂性。

主要功能：

​	•	定期合并 NameNode 的操作日志和文件系统镜像。

​	•	提供备份文件系统镜像，以防 NameNode 出现故障时进行恢复。

---

​	•	数据块存储和副本机制

HDFS 将每个文件切分成固定大小的数据块（默认是 **128MB** 或 **256MB**）。这些数据块被分散存储到集群中的多个 DataNode 上。为了提高数据的可用性和容错性，每个数据块会有多个副本（默认副本数为 **3**）。副本存储在不同的 DataNode 上，确保即使某个 DataNode 或文件块发生故障，数据仍然可以从其他副本恢复。

主要特点：

​	•	**副本数量**：每个数据块的副本数默认为 3，可以根据需要进行调整。

​	•	**副本分布策略**：副本通常会分布在**不同**的机架上，以确保机架故障时不会丢失数据。

---



#### 2.1.2 HDFS 工作原理

HDFS 的工作原理包括文件的读写流程、心跳机制和副本的分布策略等方面。理解这些原理有助于深入掌握 HDFS 的工作方式。

---

​	•	文件的读写流程

**文件写入流程**：

​	•	客户端请求将文件写入 HDFS 时，首先与 NameNode 进行交互，获取文件分块和存储位置的信息。

​	•	NameNode 返回存储块的位置列表，客户端将文件分割成多个块，并依次写入多个 DataNode。

​	•	在数据写入时，客户端按照顺序将每个数据块传输给对应的 DataNode，并保证每个块有多个副本存储在不同的 DataNode 上。

​	•	每个块的副本创建完成后，DataNode 会向 NameNode 汇报块的存储状态。

**文件读取流程**：

​	•	客户端请求从 HDFS 中读取文件时，首先与 NameNode 进行交互，获取文件所在数据块的位置。

​	•	NameNode 返回每个数据块的存储位置后，客户端直接与 DataNode 进行数据交换，按顺序从 DataNode 中读取数据。

---

​	•	心跳机制和副本分布策略

**心跳机制**：DataNode 定期向 NameNode 发送心跳信号，表明自己处于正常工作状态。如果 NameNode 在一定时间内没有收到某个 DataNode 的心跳信号，NameNode 会认为该节点发生故障，并将其存储的块的副本数进行调整。

**副本分布策略**：为了提高数据可靠性，HDFS 会将每个文件块的副本存储在不同的 DataNode 上。默认情况下，副本会分布在不同的机架上，这样可以防止机架故障时丢失数据。

---

​	•	HDFS 的容错与高可用性（HA 配置）

HDFS 通过副本机制保证数据的高可用性。如果某个 DataNode 故障，NameNode 会从其他副本中恢复数据，保持系统的可用性。HDFS 的高可用性配置通常涉及多个 NameNode（Active/Standby 模式）和备份机制。

​	1.	**高可用性配置**：通过配置两个 NameNode，其中一个为 Active 状态，另一个为 Standby 状态。Active NameNode 处理所有客户端的请求，而 Standby NameNode 在 Active NameNode 发生故障时接管工作。

​	2.	**自动故障转移**：当 Active NameNode 故障时，Standby NameNode 会自动接管，确保系统的连续性。

---



#### 2.1.3 HDFS 操作

​	•	使用命令行操作 HDFS（上传、下载、删除文件）

HDFS 的路径管理必须通过 Hadoop 的命令行工具完成，而不是通过直接操作本地文件系统。

HDFS 的目录是 Hadoop 分布式文件系统中的**虚拟路径**，而不是本地文件系统的路径。HDFS 目录是在 Hadoop 的文件系统中创建的，跟本地的文件系统目录（如 etc 文件夹）没有直接关系。

HDFS 的路径操作其实是在虚拟目录结构中进行的。这些路径和目录是用户与 HDFS 系统交互时使用的接口，它们是由 **Namenode** 管理的逻辑结构。文件的实际数据则被分割成多个块，并存储在 HDFS 中不同节点的 DataNode 上。路径命令所操作的目录并不是物理存在的，而是 HDFS 的元数据系统的一部分，帮助用户高效地管理和访问数据。

---

**hadoop fs** 是 Hadoop 提供的一个命令行工具，用于与 Hadoop 分布式文件系统（HDFS）进行交互。它允许用户像使用本地文件系统一样在 HDFS 上执行文件操作，例如创建目录、上传和下载文件、查看文件内容、删除文件等。

```hadoop fs <命令> <参数>```

常用命令

```bash
# 显示 /user/hadoop 目录下的所有文件和子目录
hdfs dfs -ls /user/hadoop

# 查看 HDFS 文件或目录内容
hadoop fs -ls /hdfs_dir/

# 查看 HDFS 文件内容
hadoop fs -cat /hdfs_dir/file.txt

# 显示文件前 N 行
hadoop fs -head /hdfs_dir/file.txt

# 显示文件最后 N 行
hadoop fs -tail /hdfs_dir/file.txt

# 上传本地文件到 HDFS
hadoop fs -put local_file /hdfs_dir/

# 从 HDFS 下载文件到本地
hadoop fs -get /hdfs_file local_dir/

# 拷贝文件到 HDFS 目录
hadoop fs -copyFromLocal local_file /hdfs_dir/

# 从 HDFS 移动文件到本地目录
hadoop fs -getmerge /hdfs_dir/ /local_dir/output.txt

# 删除 HDFS 文件
hadoop fs -rm /hdfs_file

# 递归删除目录及其内容
hadoop fs -rm -r /hdfs_dir/

# 移动文件到 HDFS 目录
hadoop fs -mv /local_file /hdfs_dir/

# 创建 HDFS 目录
hadoop fs -mkdir /hdfs_new_dir/

# 递归创建目录
hadoop fs -mkdir -p /hdfs/parent/dir/

# 查看 HDFS 目录的磁盘使用情况
hadoop fs -du /hdfs_dir/

# 查看 HDFS 目录下的文件数
hadoop fs -count /hdfs_dir/

# 显示 HDFS 文件系统的状态
hadoop fs -df -h

# 查看 HDFS 文件系统的配置信息
hadoop fs -conf

# 检查文件或目录是否存在
hadoop fs -test -e /hdfs_file_or_dir/

# 查看文件或目录权限
hadoop fs -ls -l /hdfs_file_or_dir/

# 修改文件或目录权限
hadoop fs -chmod 777 /hdfs_file_or_dir/

# 更改文件或目录的拥有者
hadoop fs -chown user:group /hdfs_file_or_dir/

# 更改文件或目录的组
hadoop fs -chgrp group /hdfs_file_or_dir/

# 查看文件的副本信息
hadoop fs -stat /hdfs_file

# 重新平衡 HDFS 集群
hadoop fs -setrep -R -w 3 /hdfs_dir/

# 文件副本恢复
hadoop fs -setrep -R 1 /hdfs_dir/

# 查看文件系统的统计信息
hadoop fs -stat %b /hdfs_file

# 列出 HDFS 中所有的文件
hadoop fs -ls -R /

# 将多个文件合并为一个
hadoop fs -getmerge /hdfs_dir/ /local_dir/output.txt
```

通过命令行对HDFS进行基本的文件操作（上传、下载、删除文件、查看目录等），加深对HDFS文件系统的理解

在HDFS中创建自己的目录，并上传一些文件，模拟一个小型数据存储系统

```bash
hadoop fs -mkdir /
```



---

​	•	Java API 操作 HDFS

具体操作详见java文件

通过 Java API 操作 HDFS，可以在 Java 程序中以编程方式与 Hadoop 分布式文件系统（HDFS）进行交互，执行文件操作、读取和写入数据等。Hadoop 提供了一套 **HDFS API，用于操作 Hadoop 分布式文件系统**。

**FileSystem**：这是操作 HDFS 文件系统的核心 API。它提供了基本的文件操作功能，如创建、删除、读取、写入、移动文件等



### **2.2 MapReduce**

**MapReduce** 是 Hadoop 的 **分布式计算框架**，用于处理大规模数据集

**与 HDFS 和 YARN 的关系**

​	•	**依赖 HDFS：** MapReduce 的输入和输出数据都存储在 HDFS 中。

​	•	**依赖 YARN：** MapReduce 使用 YARN 来管理计算资源（如 CPU 和内存），分配任务并监控任务执行。

---

#### 2.2.1 MapReduce 架构

MapReduce 的核心架构由以下组件组成：

**1.1 JobTracker 和 TaskTracker （经典架构，仅 Hadoop 1 使用）**

​	•	**JobTracker**：负责管理 MapReduce 作业。

​	•	接收作业提交请求。

​	•	负责作业分解为任务，并分配给 TaskTracker。

​	•	监控任务执行状态，处理失败任务。

​	•	**TaskTracker**：负责执行任务。

​	•	接收 JobTracker 分配的任务。

​	•	定期向 JobTracker 报告心跳和任务状态。

​	Hadoop 2 开始由 **YARN** 取代 JobTracker 和 TaskTracker。

**1.2 YARN 架构 （Hadoop 2+ 使用）**

在 YARN 架构中，MapReduce 作业运行时的管理由以下组件负责：

​	•	**ResourceManager**：管理集群资源。

​	•	负责分配计算资源给各个作业。

​	•	**NodeManager**：负责每个节点的任务执行和资源管理。

​	•	**ApplicationMaster**：为每个 MapReduce 作业启动一个 AM 进程，负责具体作业的任务调度和监控。

---



#### 2.2.2 MapReduce 工作原理

​	•	**1. MapReduce 编程模型**

MapReduce 是一种编程模型和分布式计算框架，用于大规模数据处理。它的核心思想是将任务分为两个阶段：

​	•	**Map 阶段**：将输入数据**处理为键值对**的形式。

​	•	**Reduce 阶段**：对 Map 输出的键值对进行**分组和聚合**，生成最终结果。

每个 Map 和 Reduce 阶段通常由开发者实现具体逻辑，而 MapReduce 框架负责调度、分配资源、容错和数据传输。

---

​	•	**2. 输入与输出：键值对的处理**

**输入数据格式**：

​	•	MapReduce 的输入数据通常以键值对的形式存在，键为偏移量（默认情况下），值为输入数据的每一行。

​	•	开发者可以自定义输入格式（InputFormat）以适应特定数据需求。

**输出数据格式**：

​	•	Map 阶段输出为一组新的键值对，供 Shuffle 和 Reduce 阶段处理。

​	•	Reduce 阶段最终生成结果的键值对，存储在 HDFS 或其他目标存储中。

示例：

输入文件：

`hello world
hello Hadoop`

Map输出：

`(hello, 1)
(world, 1)
(hello, 1)
(Hadoop, 1)`

Reduce输出：

`(hello, 2)
(world, 1)
(Hadoop, 1)`

---

**Map 阶段：键值对的映射处理**

​	1.	**任务**：

​	•	每个输入分片（Split）被分配给一个 Map Task。

​	•	Map Task 将每条输入记录转换为零个或多个键值对。

​	2.	**逻辑**：

​	•	Map 函数由开发者实现（继承自 Mapper 类）。

​	•	输出的键值对被缓存在内存中，随后写入本地磁盘，供 Shuffle 阶段使用。

---

**Shuffle 阶段：中间数据的排序与分区**

​	1.	**任务**：

​	•	收集所有 Map 阶段的输出数据，按键进行排序和分区。

​	•	不同分区的数据被分发到相应的 Reduce Task。

​	2.	**过程**：

​	•	**排序**：中间键值对按键进行排序（字典序）。

​	•	**分区**：根据自定义或默认的分区器（Partitioner），将相同键值对分配到同一分区。

---

**Reduce 阶段：分组与聚合**

​	1.	**任务**：

​	•	对每个分区中的键值对进行分组，执行聚合逻辑。

​	•	Reduce 函数由开发者实现（继承自 Reducer 类）。

​	2.	**逻辑**：

​	•	接收由 Shuffle 阶段分组后的键值对。

​	•	执行逻辑操作，例如求和、统计、排序等。

---

​	•	**3. MapReduce 执行流程**

​	•	Job 提交 → 切分任务 → 分发 Map 和 Reduce → 最终输出

​	1.	**Job 提交**：

​	•	客户端将 Job 提交给 ResourceManager。

​	•	包括 Jar 包、配置文件和输入数据路径。

​	2.	**任务切分**：

​	•	将输入数据切分为多个 Splits，每个 Split 通常为一个 HDFS 块（默认 128 MB）。

​	3.	**分发任务**：

​	•	ResourceManager 分配 Map 和 Reduce 任务。

​	•	NodeManager 在节点上启动 Task。

​	4.	**执行任务**：

​	•	Map Task 处理输入 Splits，生成中间键值对。

​	•	Shuffle 阶段排序并分发数据。

​	•	Reduce Task 聚合数据并生成最终结果。

​	5.	**输出结果**：

​	•	Reduce 阶段的结果存储在 HDFS 中。

---

​	•	**4. MapReduce 的容错机制**

​	•	Map/Reduce 任务的重试与数据恢复

**Map/Reduce 任务的重试**：

​	•	如果任务失败，框架会在另一个节点上重新运行该任务。

​	•	默认情况下，每个任务最多重试 4 次（可配置）。

**数据恢复**：

​	•	MapReduce 使用 HDFS 的副本机制保证数据可靠性。

​	•	任务的中间数据存储在本地磁盘，失败时可重用 HDFS 原始数据重新计算。

---



#### 2.2.3 MapReduce 操作

**MapReduce 的使用流程**

MapReduce 的使用流程主要是：**准备需求 → 编写程序 → 上传数据 → 提交作业 → 查看结果 → 分析日志**

**1. 准备工作**

​	1.	**确定需求**

确定要实现的任务逻辑（例如：单词计数、排序、去重等）。

​	2.	**准备环境**

​	•	Hadoop 集群已搭建并正常运行。

​	•	输入数据已上传到 HDFS。

**2. 编写 MapReduce 程序**

​	1.	**编写 Mapper 类**

定义 Map 阶段逻辑，处理输入数据并输出中间键值对 (key, value)。

​	2.	**编写 Reducer 类**

定义 Reduce 阶段逻辑，对中间键值对按键分组，并生成最终结果。

​	3.	**编写 Driver 类**

用于配置和提交 MapReduce 作业。

​	4.	**编译打包程序**

你的 Java MapReduce 程序需要被编译成 .class 文件，并打包成 .jar 文件。

打包可以使用 Maven、Gradle 或手动 javac 和 jar 命令完成。

**3. 提交 MapReduce 作业**

**准备数据**

将输入数据上传到 HDFS：

```bash
hadoop fs -mkdir /input

hadoop fs -put input.txt /input
```

**提交作业**

使用 hadoop jar 命令提交作业：

```bash
hadoop jar WordCount.jar WordCountDriver /input /output
```

**4. 查看结果**

​	1.	**查看输出文件**

输出结果存储在 HDFS 的 /output 目录下：

```bash
hadoop fs -cat /output/part-r-00000
```

​	2.	**下载到本地**

如果需要将结果下载到本地：

```bash
hadoop fs -get /output/part-r-00000 ./result.txt
```

**5. 分析日志**

​	1.	**实时查看日志**

提交作业时，控制台会显示作业的进度日志，包含：

​	•	Map 和 Reduce 任务的执行进度。

​	•	作业是否成功完成。

​	2.	**查看详细日志**

​	•	**使用 Yarn**：

```bash
yarn logs -applicationId <application_id>
```

​	•	**查看历史记录**（需要启动 JobHistory Server）。

**6. 验证结果**

​	1.	验证输出结果是否符合预期。

​	2.	如果结果有误，可以调试代码或查看日志分析问题。

---



#### **2.2.4 实践案例**

​	•	**单词计数（Word Count）**

实现代码详见 WordCount.java

**手动配置演示**

首先需要创建一个这样的项目目录结构

```bash
WordCountProject/
├── src/
│   ├── WordCount.java
├── bin/     # （编译后的 .class 文件）
```

以下是详细的步骤：

**1. 检查 Hadoop 安装目录**

在手动配置过程中，首先需要找到你的 Hadoop 安装目录。假设你已经安装了 Hadoop，可以通过以下命令检查 Hadoop 的位置：

```bash
echo $HADOOP_HOME
```



**2. 收集所需的 JAR 文件**

Hadoop 的 Java 程序依赖多个 JAR 文件。以下是常用的依赖 JAR 文件位置：

​	•	**Hadoop 通用依赖**：$HADOOP_HOME/share/hadoop/common/

​	•	**MapReduce 客户端依赖**：$HADOOP_HOME/share/hadoop/mapreduce/

​	•	**HDFS 依赖**：$HADOOP_HOME/share/hadoop/hdfs/

**需要的关键 JAR 文件：**

​	•	hadoop-common-<version>.jar

​	•	hadoop-mapreduce-client-core-<version>.jar

​	•	hadoop-hdfs-<version>.jar



将以上 JAR 文件拷贝到项目的 lib 文件夹中。例如：

```bash
mkdir -p ~/WordCountProject/lib # 创建 lib 文件夹

cp $HADOOP_HOME/share/hadoop/common/hadoop-common-*.jar ~/WordCountProject/lib

cp $HADOOP_HOME/share/hadoop/mapreduce/hadoop-mapreduce-client-core-*.jar ~/WordCountProject/lib

cp $HADOOP_HOME/share/hadoop/hdfs/hadoop-hdfs-*.jar ~/WordCountProject/lib
```



**3. 设置 CLASSPATH**

CLASSPATH 是一个环境变量，指定了 Java 在运行和编译时如何查找类文件（.class）以及外部库（.jar）。没有正确设置 CLASSPATH，Java 无法找到项目依赖的 JAR 包，从而导致 import 语句的红线或运行时错误。

**不设置 CLASSPATH**

​	•	每次编译和运行都需要手动指定 -classpath

**全局设置 CLASSPATH **

​	•	方便管理项目，避免重复设置。

​	•	让系统知道所有外部依赖的路径，尤其是当项目需要大量依赖时。

---

手动为项目设置 CLASSPATH，以便编译时可以找到依赖的 JAR 文件。

在终端中执行以下命令：

```bash
export CLASSPATH=$(find ~/WordCountProject/lib -name "*.jar" | tr '\n' ':'):$CLASSPATH
```

这会将 lib 文件夹中所有 JAR 文件添加到 CLASSPATH 中。

使用Vscode时

在项目的 VSCode 设置中，**需要手动添加依赖路径**：

​	•	打开 VSCode 的 **设置（Settings）**。

​	•	搜索 java.project.referencedLibraries。

​	•	点击 **Edit in settings.json**，然后添加依赖路径

```json
{
  "java.project.referencedLibraries": [
    "lib/*.jar" // 这里修改为要用的 Hadoop 的 jar包所在的绝对路径
  ]
}
```

---

**Vscode Tips**

---

VSCode 中的设置文件有几种不同的层级：

​	1.	**用户设置（User Settings）**：这些设置对所有 VSCode 项目和工作区有效。它们存储在全局配置文件中。

​	2.	**工作区设置（Workspace Settings）**：这些设置仅对当前工作区（即当前项目）有效。它们存储在工作区的 .vscode/settings.json 文件中。

​	3.	**文件夹设置（Folder Settings）**：这些设置仅对特定文件夹有效，适用于更复杂的多根工作区配置。

因此，当你修改 settings.json 文件时，如果它位于 .vscode 文件夹下，那么它只会影响当前打开的项目（工作区）。而如果你想要对所有项目生效，你可以修改 **用户设置**。

**如何让设置对所有项目生效？**

要让设置对所有项目有效，你需要在 **用户设置** 中配置，而不是在工作区的 settings.json 中。

**修改用户设置：**

​	1.	打开 VSCode，点击左下角的齿轮图标（设置按钮）。

​	2.	选择 **Settings**（设置）。

​	3.	在右上角的搜索框中，输入 settings.json，并点击 **Edit in settings.json**（在 settings.json 中编辑）。

​	4.	在 settings.json 中添加或修改配置项（这时修改的设置会对所有工作区生效）。

例如，你可以添加如下配置：

```json
{
 "java.project.referencedLibraries": [
  "lib/**/*.jar"
 ]
}
```

**如何让 settings.json 只对某个特定工作区有效？**

如果你只希望设置对当前项目有效，就需要在该项目的 .vscode/settings.json 文件中进行配置。例如：

```json
{
 "java.project.referencedLibraries": [
  "lib/**/*.jar"
 ]
}
```

**总结**

​	•	settings.json 文件位于 .vscode 文件夹下时，设置对当前项目有效。

​	•	若要设置全局生效，修改 **用户设置**。

---



**4. 编译 Java 程序**

---

**Tips：** 确保在重新编译之前清理 classes 目录，这样可以避免老的编译结果和不必要的文件结构干扰。

---

确保 WordCount.java 文件在 src 文件夹下，例如：

~/WordCountProject/src/WordCount.java



**编译命令：**

使用 javac 命令编译代码，并将 .class 文件输出到 classes 目录：

```bash
mkdir -p ~/WordCountProject/classes

javac -d ~/WordCountProject/classes ~/WordCountProject/src/WordCount.java
```

当编译 src/WordCount.java 文件时，javac 会按照 src 中的包结构生成相应的目录层级，并将 .class 文件输出到指定的目录。

如果希望 .class 文件直接放在 classes 目录下，而不保留源代码的包结构，可以将源文件中去掉包声明，或者使用 -d 参数结合适当的目录结构。

现在的目录结构：

```bash
WordCountProject
.
├── bin
├── classes
│   └── WordCountProject
│       └── src
│           ├── WordCount$WordMapper.class
│           ├── WordCount$WordReducer.class
│           └── WordCount.class
├── lib
│   ├── hadoop-common-3.4.1-tests.jar
│   ├── hadoop-common-3.4.1.jar
│   ├── hadoop-hdfs-3.4.1-tests.jar
│   ├── hadoop-hdfs-3.4.1.jar
│   ├── hadoop-hdfs-client-3.4.1-tests.jar
│   ├── hadoop-hdfs-client-3.4.1.jar
│   ├── hadoop-hdfs-httpfs-3.4.1.jar
│   ├── hadoop-hdfs-native-client-3.4.1-tests.jar
│   ├── hadoop-hdfs-native-client-3.4.1.jar
│   ├── hadoop-hdfs-nfs-3.4.1.jar
│   ├── hadoop-hdfs-rbf-3.4.1-tests.jar
│   ├── hadoop-hdfs-rbf-3.4.1.jar
│   └── hadoop-mapreduce-client-core-3.4.1.jar
└── src
    └── WordCount.java

7 directories, 17 files
```



**5. 打包 JAR 文件**

将编译好的 .class 文件打包为 JAR 文件，以便提交到 Hadoop 集群。

**打包命令：**

```bash
cd ~/WordCountProject/classes
jar -cvf wordcount.jar *
```

这会在 classes 文件夹中生成 wordcount.jar 文件。



**6. 运行 MapReduce 程序**

将打包好的 wordcount.jar 文件提交到 Hadoop 集群运行。

**运行命令：**

```bash
hadoop jar ~/WordCountProject/classes/wordcount.jar WordCount <input_path> <output_path>
```

​	•	<input_path> 是 HDFS 上的输入文件路径。

​	•	<output_path> 是 HDFS 上的输出路径。

注意：如果你的 WordCount 类在 .java 文件中声明了 package，那么运行命令时，需要指定主类的完整类名，包括包名:

```bash
hadoop jar "/Users/sscc/Desktop/personal/Career_skills/Hadoop/WordCountProject/classes/wordcount.jar" WordCountProject.src.WordCount /user/sscc/input /user/sscc/output
```



---

**Tips：**如果输入路径是一个文件夹，Hadoop 会自动处理该文件夹中的所有文件。

---



**7. 检查结果**

输出结果会保存在 HDFS 的 <output_path> 目录下，可以通过以下命令查看结果文件：

```bash
hadoop fs -ls /user/sscc/output
```

```bash
Found 2 items
-rw-r--r--   1 sscc supergroup          0 2025-01-13 14:36 /user/sscc/output/_SUCCESS
-rw-r--r--   1 sscc supergroup        109 2025-01-13 14:36 /user/sscc/output/part-r-00000
```

```bash
hadoop fs -cat /user/sscc/output/part-r-00000
```

```bash
and	1
apple	1
blue	1
bottle	1
boy	3
chips	1
exercise	1
fish	1
handsome	1
health	1
orange	1
sausage	1
water	3
```

此时的目录结构：

```bash
WordCountProject
.
├── bin
├── classes
│   ├── WordCountProject
│   │   └── src
│   │       ├── WordCount$WordMapper.class
│   │       ├── WordCount$WordReducer.class
│   │       └── WordCount.class
│   └── wordcount.jar
├── lib
│   ├── hadoop-common-3.4.1-tests.jar
│   ├── hadoop-common-3.4.1.jar
│   ├── hadoop-hdfs-3.4.1-tests.jar
│   ├── hadoop-hdfs-3.4.1.jar
│   ├── hadoop-hdfs-client-3.4.1-tests.jar
│   ├── hadoop-hdfs-client-3.4.1.jar
│   ├── hadoop-hdfs-httpfs-3.4.1.jar
│   ├── hadoop-hdfs-native-client-3.4.1-tests.jar
│   ├── hadoop-hdfs-native-client-3.4.1.jar
│   ├── hadoop-hdfs-nfs-3.4.1.jar
│   ├── hadoop-hdfs-rbf-3.4.1-tests.jar
│   ├── hadoop-hdfs-rbf-3.4.1.jar
│   └── hadoop-mapreduce-client-core-3.4.1.jar
├── src
│   └── WordCount.java
└── testfile.txt

7 directories, 19 files
```

至此完成！

**总结**

手动配置虽然稍微复杂，但可以灵活控制项目依赖。以上步骤包括依赖收集、CLASSPATH 设置、编译、打包和运行的完整流程。

---



​	•	**数据排序（Sorting）**

​	•	**数据去重（Deduplication）**

​	•	**Top N 问题**

​	•	**倒排索引**

---



#### 2.2.5 性能优化

​	•	**自定义分区器（Partitioner）**：提高数据分布的均匀性

​	•	**自定义排序器（Comparator）**：优化 Reduce 阶段的处理效率

​	•	**Combiner 的使用**：在本地聚合中间数据，减少网络传输

​	•	**输入输出格式（InputFormat/OutputFormat）**：选择和自定义数据格式以提升性能

---



#### 2.2.6 日志分析

​	•	查看 Hadoop 提供的 Job 历史日志

​	•	分析 Map 和 Reduce 阶段的任务细节

### **2.3 YARN（Yet Another Resource Negotiator）**

**YARN** 是 Hadoop 的 **资源管理框架**，用于分配和管理集群中的计算资源（如 CPU 和内存），它**分离了资源管理和作业调度**的功能，提升了集群资源的利用率和作业的执行效率。它可以**同时支持多种计算框架**（如 MapReduce、Spark、Flink 等）

MapReduce 是一个计算框架，MapReduce 作业以 YARN 作业形式运行，**YARN 可运行多种作业，而不仅是 MapReduce**

**与 HDFS 和 MapReduce 的关系**

​	•	**依赖 HDFS：** YARN 管理的计算任务通常会处理存储在 HDFS 中的数据。

​	•	**管理 MapReduce：** MapReduce 是运行在 YARN 上的一个计算框架，YARN 提供资源支持并监控其任务执行。

​	•	**支持多种框架：** 除了 MapReduce，YARN 还可以运行其他计算框架（如 Spark）。

---



#### 2.3.1 YARN 的架构

YARN 的架构包括以下几个关键组件：

**ResourceManager (RM)**

​	•	资源调度与分配的核心组件。

​	•	包含两个主要子模块：

​	•	**Scheduler**: 按需分配资源，不负责任务监控与执行。

​	•	**ApplicationManager**: 负责管理应用程序生命周期。

**NodeManager (NM)**

​	•	每个集群节点的守护进程。

​	•	负责容器（Container）的管理、资源监控和任务执行。

**ApplicationMaster (AM)**

​	•	每个应用程序独立的资源与任务管理者。

​	•	与 ResourceManager 和 NodeManager 协作。

**Container**

​	•	YARN 的资源分配单元，包含 CPU、内存等资源。

**YARN Timeline Server**

​	•	提供任务运行历史的记录和查询服务。



#### 2.3.2 YARN 工作原理

​	•	资源分配与任务调度

​	•	多种框架在 YARN 上运行（Spark、Hive 等）

​	•	YARN的工作流程

---

**资源分配与任务调度**

​	•	**资源分配**:

YARN 的核心是 **ResourceManager** 和 **NodeManager**。

​	•	ResourceManager 负责全局资源分配与调度。

​	•	NodeManager 负责节点资源管理和任务的具体执行。

​	•	**资源模型**:

YARN 使用 **Container** 作为资源分配的最小单位，一个 Container 包含一部分 CPU、内存等资源。

​	•	**调度器**:

YARN 提供了多种调度器（FIFO 调度器、容量调度器、公平调度器），可以根据不同需求灵活配置资源分配策略。

​	•	**任务调度**:

​	•	当用户提交作业时，YARN 启动一个 **ApplicationMaster (AM)**，负责管理该作业的生命周期。

​	•	ApplicationMaster 会向 ResourceManager 请求资源（Container）。

​	•	ResourceManager 分配资源后，ApplicationMaster 与 NodeManager 协作，在分配的 Container 中启动任务。

---

**多种框架在 YARN 上运行**

YARN 的灵活性使其可以运行多种计算框架，不局限于 MapReduce:

​	•	**Spark**:

Spark 是基于内存计算的快速大数据处理框架。通过 YARN 提供的资源管理，Spark 作业可以高效调度和执行。

​	•	**Hive**:

Hive 提供类 SQL 的数据分析功能，可以在 YARN 上运行，将 SQL 转换为 MapReduce 或 Tez 作业。

​	•	**Tez**:

Tez 是一个 DAG（有向无环图）计算框架，优化了 MapReduce 作业的性能。

​	•	**Flink**:

Flink 是一个支持实时流处理和批处理的框架，也可以依赖 YARN 来分配资源。

​	•	**HBase**:

HBase 是基于 Hadoop 的分布式数据库，可以通过 YARN 提供资源支持其 RegionServer 的管理。

---

**YARN的工作流程**

1. **提交应用程序**：用户提交作业，客户端向 ResourceManager 发送请求。

2. **ResourceManager 分配资源**：ResourceManager 启动 ApplicationMaster 并为其分配资源。

3. **启动 ApplicationMaster**：ApplicationMaster 在容器内运行，向 ResourceManager 请求更多资源。

4. **NodeManager 启动容器**：NodeManager 分配计算资源并启动容器来运行任务。

5. **任务执行与监控**：ApplicationMaster 监控任务执行，并与 NodeManager 协调。

6. **资源释放与作业完成**：任务完成后，资源被释放，作业状态反馈给 ResourceManager。

7. **容错与恢复**：如果容器失败，ResourceManager 会重新调度资源。

---



#### 2.3.3 YARN 高级功能

​	•	YARN 容错机制

​	•	YARN 集群监控工具（如 ResourceManager UI）

---

**YARN 容错机制**

YARN 的容错机制确保了集群的高可用性和任务的可靠性。其核心容错机制包括以下几个方面：

​	•	**ResourceManager 容错**

**ResourceManager** 是 YARN 的资源管理和调度的核心组件，但它的单点故障可能导致整个集群的资源调度瘫痪。为此，YARN 引入了 **ResourceManager 高可用性（HA）** 机制，通过配置两个 ResourceManager 节点（一个为活跃，另一个为备用），在活跃节点发生故障时，备用节点会自动接管，确保服务不中断。

​	•	**NodeManager 容错**

**NodeManager** 是 YARN 的节点管理者，负责节点的资源管理和监控。NodeManager 本身也具有容错机制。如果某个 NodeManager 节点发生故障，ResourceManager 会重新调度该节点上的任务，尽量将任务迁移到其他可用的 NodeManager 节点上。

​	•	**ApplicationMaster 容错**

**ApplicationMaster** 负责管理每个应用的生命周期，若某个 ApplicationMaster 宕机或失败，YARN 会尝试重新启动新的 ApplicationMaster。这样可以保证即使应用的 Master 失败，应用本身仍然能够继续运行。

---

**YARN 集群监控工具**

为了有效地管理和监控 YARN 集群，YARN 提供了多种监控工具，帮助用户查看集群状态、资源使用情况以及各个作业的执行情况。

​	•	**ResourceManager UI**：

**ResourceManager UI** 是 YARN 提供的 Web 界面，用于展示集群资源的使用情况和各个应用的状态。在这个 UI 中，用户可以查看到集群中所有节点的资源使用情况、运行的应用程序、应用的资源分配情况等详细信息。

通过 ResourceManager UI，用户还可以查看每个任务的日志、执行的详细信息，便于排查问题和进行性能调优。

​	•	**NodeManager UI**：

每个 **NodeManager** 节点也有自己的 Web 界面，显示该节点的资源使用情况、当前运行的容器以及容器的详细信息。NodeManager UI 可以帮助管理员了解每个节点的资源利用率和任务执行情况。

​	•	**YARN Timeline Server**：

**Timeline Server** 是 YARN 提供的一个用于收集和查看 YARN 应用程序历史信息的服务。通过 Timeline Server，用户可以查看历史应用的执行日志、容器信息、资源利用率等，有助于调试和优化集群中的作业。

​	•	**YARN ResourceManager 日志**：

YARN 会记录关于集群资源管理的详细日志，这些日志包括了作业调度、资源分配、任务执行等信息。通过分析这些日志，可以帮助管理员识别潜在问题，并对集群进行优化。



## **3. Hadoop 生态系统**



### **3.1 数据存储**

​	•	**HBase**

​	•	HBase 的架构（RegionServer、Master）

​	•	HBase 数据模型（RowKey、Column Family）

​	•	HBase 和 HDFS 的关系

​	•	**Parquet 和 ORC**

​	•	列式存储的优势

​	•	适用场景与性能对比



### **3.2 数据处理**

​	•	**Hive**

​	•	Hive 的架构与工作原理

​	•	HiveQL 基本语法（查询、分区、分桶）

​	•	Hive 的 UDF 和性能优化

​	•	**Pig**

​	•	Pig Latin 语法与使用场景

​	•	Pig 与 MapReduce 的结合



### **3.3 数据集成**

​	•	**Sqoop**

​	•	从关系数据库导入数据到 HDFS

​	•	从 HDFS 导出数据到关系数据库

​	•	**Flume**

​	•	日志数据采集

​	•	Flume 的架构与配置



### **3.4 数据协调与调度**

​	•	**Zookeeper**

​	•	分布式协调服务

​	•	Zookeeper 的数据模型与使用场景

​	•	**Oozie**

​	•	Hadoop 工作流调度工具

​	•	定义和运行复杂数据管道



## **4. 高级功能与优化**

​	•	**4.1 性能优化**

​	•	数据分区与分块设计

​	•	MapReduce 作业调优（Combiner、Skew 处理）

​	•	YARN 资源配置优化

​	•	**4.2 高可用性与容错**

​	•	HDFS 高可用性配置（HA 模式）

​	•	NameNode 崩溃的处理与恢复

​	•	**4.3 安全性**

​	•	Kerberos 集群认证

​	•	HDFS 文件权限与 ACL 配置

​	•	**4.4 Hadoop 集群监控**

​	•	使用 Ambari、Ganglia 进行集群监控

​	•	集群日志管理（Log Aggregation）



## **5. 实践与项目**

​	•	**5.1 小型项目**

​	•	HDFS 的日志存储系统

​	•	MapReduce 实现基本数据分析（如 Top N 分析）

​	•	**5.2 综合项目**

​	•	**日志分析系统**：

​	•	使用 Flume 采集日志。

​	•	将日志存储到 HDFS。

​	•	使用 Hive 进行查询和分析。

​	•	**数据导入与分析**：

​	•	使用 Sqoop 从 MySQL 导入数据到 HDFS。

​	•	使用 MapReduce 或 Hive 进行大数据处理。

​	•	**用户行为分析**：

​	•	Kafka + Flume 采集实时数据。

​	•	使用 HDFS 存储。

​	•	使用 Spark 进行实时分析。



## **6. 学习工具与资源**

​	•	**官方文档**：Hadoop 官网

​	•	**参考书籍**：

​	•	《Hadoop 权威指南》

​	•	《大数据之路：从数据到智能》

​	•	**在线课程**：

​	•	Coursera 或 Udemy 的 Hadoop 系列课程

​	•	国内大厂开源学习资料（如阿里云、腾讯课堂）

​	•	**实践环境**：

​	•	使用虚拟机搭建 Hadoop 单节点环境

​	•	云服务平台（AWS、阿里云）的 Hadoop 集群



**总结**

这套学习框架涵盖了 Hadoop 的理论、实践和优化方法，帮助你从零开始系统学习，最终实现实际项目的开发。