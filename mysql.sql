-- MySQL dump 10.13  Distrib 5.7.32, for Linux (x86_64)
--
-- Host: localhost    Database: myblog
-- ------------------------------------------------------
-- Server version	5.7.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_blog`
--

DROP TABLE IF EXISTS `t_blog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_blog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `appreciation` bit(1) NOT NULL,
  `commentabled` bit(1) NOT NULL,
  `content` longtext,
  `create_time` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `first_picture` varchar(255) DEFAULT NULL,
  `flag` varchar(255) DEFAULT NULL,
  `published` bit(1) NOT NULL,
  `recomment` bit(1) NOT NULL,
  `share_statement` bit(1) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `views` int(11) DEFAULT NULL,
  `type_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_blog`
--

LOCK TABLES `t_blog` WRITE;
/*!40000 ALTER TABLE `t_blog` DISABLE KEYS */;
INSERT INTO `t_blog` VALUES (2,_binary '\0',_binary '','<h1 id=\"myblog\">Myblog</h1>\n<h4 id=\"介绍\">介绍</h4>\n<p>​		Myblog是一个前后端分离的个人博客,可用于个人博客建站使用和SpringBoot+Vue开发练习.</p>\n<p>Myblog在线体验<a href=\"Https://huanglin.online\" target=\"_blank\">HLBolog</a>\n博客源码 <a href=\"https://github.com/huanglin233/myblog\" target=\"_blank\">Github</a> <a href=\"https://gitee.com/huanglin_only/myblog\" target=\"_blank\">码云</a></p>\n<h4 id=\"技术栈版本介绍\">技术栈/版本介绍:</h4>\n<ul>\n<li>博客后端\n<ul>\n<li>JDK 1.8</li>\n<li>SpringBoot 2.2.6</li>\n<li>Mybatis 1.3.2</li>\n<li>Mysql 5.7</li>\n<li>Druid 1.1.17</li>\n<li>Shiro 1.4</li>\n<li>Jwt 3.2.0</li>\n<li>Commonmark 0.10.0</li>\n<li>Bytebuddy 1.10.9</li>\n<li>Fastdfs 1.26.1</li>\n<li>Swagger 2.9.2</li>\n<li>Knife4j 2.0.4</li>\n<li>Jsoup 1.10.2</li>\n<li>Log4j 1.2.17</li>\n</ul>\n</li>\n<li>博客前端\n<ul>\n<li>Webpack 3.6.0</li>\n<li>Vue 2.5.2</li>\n<li>Semantic UI - 2.4.2</li>\n</ul>\n</li>\n</ul>\n','2021-01-12 08:36:33.367000','博客要用于记录生活\\学习\\技术',NULL,'原创',_binary '',_binary '',_binary '','博客简介','2021-01-12 08:39:56.645000',10,33,1),(3,_binary '\0',_binary '','<h3 id=\"内容\">内容</h3>\n<p>给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 的那 两个 整数，并返回它们的数组下标。</p>\n<p>你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。你可以按任意顺序返回答案。</p>\n<h3 id=\"示例\">示例</h3>\n<p>输入：nums = [2,7,11,15], target = 9\n输出：[0,1]\n解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。</p>\n<h3 id=\"java解法\">JAVA解法</h3>\n<pre><code class=\"language-java\">class Solution {\n    public int[] twoSum(int[] nums, int target) {\n	    // 创建一个Map用于后面存储存储数据\n         Map&lt;Integer, Integer&gt; map = new HashMap&lt;Integer, Integer&gt;();\n         for(int i = 0; i &lt; nums.length; i++) {\n		 	// 使用target与当前循环的差值是否存在于map中,如果存在就去取出差值数据在nums中的下标,\n			// 这样做就可以在一次的nums循环中中得出结果\n             if(map.keySet().contains(target - nums[i])){\n                 int ret[] = {map.get(target - nums[i]), i};\n             return ret;\n         } else {\n		 // 第一次循环和之后循环的差值如果map中不存在,就把当前循环的值作为key,在nums的下标作为value存在map中.\n             map.put(nums[i], i);\n         }\n     }\n    return null;\n  }\n}\n</code></pre>\n<h3 id=\"c解法\">C解法</h3>\n<pre><code class=\"language-C\">/**\n * Note: The returned array must be malloced, assume caller calls free().\n */\nint* twoSum(int* nums, int numsSize, int target, int* returnSize){\n    int i,j;\n    int *ret = malloc(sizeof(int) * 2);\n    for (i = 0; i &lt; numsSize; i++)\n    {\n        int tag1 = nums[i];\n		// 给出target与当前循环值的差值\n        int tag2 = target-tag1;\n		// 用差值循环nums数组看是否存在当前差值,存在则返回当前两个循环的索引值\n        for (j = i + 1; j &lt; numsSize; j++)\n        {\n            if(tag2 == nums[j]){\n                ret[0] = i;\n                ret[1] = j;\n                *returnSize = 2;\n                return ret;\n            }\n        }\n    }\n	*returnSize = 0;\n	 return NULL;\n}\n</code></pre>\n<h3 id=\"javascript解法\">JavaScript解法</h3>\n<pre><code class=\"language-JavaScript\">/**\n * @param {number[]} nums\n * @param {number} target\n * @return {number[]}\n */\nvar twoSum = function(nums, target) {\n   // 返回数组下角标1\n    var fast = 0;\n	// 返回数组下角标2\n    var last = 0;\n	// 循环数组\n    for(; fast &lt; nums.length; fast++) {\n		// 算出target和当前循环的插值\n        let last_value = target -  nums[fast];\n		// 判断差值是否存在于当前数组中,存在返回差值所在数组中的索引,否者返回-1\n        last = nums.findIndex(n =&gt; n === last_value);\n		如果返回值不为-1且不是当前值的在数组中索引,则找到满足条件,并返回两个数组下标值.\n        if(last !== -1 &amp;&amp; last != fast) {\n            let arr = [fast, last]\n            return arr;\n    	}\n	}\n};\n</code></pre>\n<h3 id=\"scala解法\">Scala解法</h3>\n<pre><code class=\"language-scala\">object Solution {\n    def twoSum(nums: Array[Int], target: Int): Array[Int] = {\n		// 改解法和java解法一致,利用了map进行的存储.\n        val map  = scala.collection.mutable.Map[Int, Int](); \n        for(i &lt;- 0 to nums.length) {\n			// 判断target和当前循环的差值是否存在map中,\n			// 如果不存在则把当前值放入map中,如果存在就返回两个值在nums中数组索引.\n            if(map.exists(x =&gt; x._1 == (target - nums(i)))) {\n                val ret : Array[Int] = new Array[Int](2);\n                ret(0) = map(target - nums(i));\n                ret(1) = i;\n				return ret;\n			} else {\n				val value : Int = nums(i);\n				map += (value -&amp;gt; i);\n			}\n		}\n		return null;\n	}\n}\n</code></pre>\n','2021-01-31 14:55:43.161000','LeetCode算法题练习之两数之和','https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2505295436,1963809141&fm=26&gp=0.jpg','原创',_binary '',_binary '\0',_binary '\0','两数之和','2021-02-22 03:08:13.516000',23,34,1),(9,_binary '\0',_binary '','<h1 style=\"text-align:center;\">两数相加</h1>\n\n## 内容\n\n	给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。\n	请你将两个数相加，并以相同形式返回一个表示和的链表。你可以假设除了数字 0 之外，这两个数都不会以 0 开头。\n## 示例\n\n	输入：l1 = [2,4,3], l2 = [5,6,4]\n	输出：[7,0,8]\n	解释：342 + 465 = 807.\n## JAVA解法\n\n```java\n/**\n * Definition for singly-linked list.\n * public class ListNode {\n *     int val;\n *     ListNode next;\n *     ListNode() {}\n *     ListNode(int val) { this.val = val; }\n *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }\n * }\n */\nclass Solution {\n    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {\n        ListNode la = null; // 头结点\n        ListNode le = null; // 临时对象地址储存结点,最后作为尾结点\n        int carry = 0; // 进位,0代表不进位,1代表进位\n        int i = 0;\n        while(l1 != null || l2 != null) {\n            int sum = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + carry; \n			// 通过判断listnode是否为空,不为空就去除val进行相加并且加上carry\n            if(sum > 9) {\n                sum = sum % 10;\n                carry = 1;\n            } else {\n                carry = 0;\n            }\n\n            if(le == null){\n                la = le = new ListNode(sum);     \n            } else {\n                le.next = new ListNode(sum);\n                le = le.next;\n            }\n			\n            if(l1 != null) {\n                l1 = l1.next;\n            }\n\n            if(l2 != null) {\n                l2 = l2.next;\n            }\n        }\n        if(carry == 1) {\n            le.next = new ListNode(carry);\n        }\n        return la;\n    }\n}\n```\n## C解法\n\n```c\n/**\n * Definition for singly-linked list.\n * struct ListNode {\n *     int val;\n *     struct ListNode *next;\n * };\n */\n\nstruct ListNode* addTwoNumbers(struct ListNode* l1, struct ListNode* l2){\n    int sum = 0;\n    int carry = 0; // 0表示不进位,1表示进位\n    struct ListNode* la = NULL; // 头结点\n    struct ListNode* le = NULL; // 声明临时存储结点\n    do{\n        if(l1 && l2) { // 如果l1和l2链表当前结点值都不为零\n            sum = l1->val + l2->val + carry; // 两个链表当前结点值相加\n            isCarry(&sum, &carry);\n            if(le != NULL) { // 判断当前结点是否为首结点\n                le->next = malloc(sizeof(struct ListNode));\n                le->next->val  = sum;\n                le = le->next;\n                le->next = NULL;\n            } else {\n                la = le = malloc(sizeof(struct ListNode)); // 分配空间\n                le->val = sum;\n                la->next = le;\n                le->next = NULL;\n            }\n        } else if(l1){ // 如果l2没有值,l1还有值\n            sum = l1->val + carry;\n            isCarry(&sum, &carry);\n            le->next = malloc(sizeof(struct ListNode));\n            le->next->val = sum;\n            le = le->next;\n            le->next = NULL;\n        } else if(l2) { // 如果l1没有值,l2还有值\n            sum = l2->val + carry;\n            isCarry(&sum, &carry);\n            le->next = malloc(sizeof(struct ListNode));\n            le->next->val = sum;\n            le = le->next;\n            le->next = NULL;\n        }\n\n        if(l1) {\n            l1 = l1->next;\n        }\n\n        if(l2) {\n            l2 = l2->next;\n        }\n    }while(l1 || l2);\n    if(carry == 1) {\n        le->next = malloc(sizeof(struct ListNode));\n        le->next->val = carry;\n        le = le->next;\n        le->next = NULL;\n    }\n\n    return la;\n}\nvoid isCarry(int *sum, int *carry) {\n    if(*sum > 9) { // 如果值大于9就进位加1\n        *carry = 1;\n        *sum = *sum % 10;\n    } else {\n        *carry = 0;\n    }\n}\n```\n## JavaScript解法\n\n```javascript\n/**\n * Definition for singly-linked list.\n * function ListNode(val) {\n *     this.val = val;\n *     this.next = null;\n * }\n */\n/**\n * @param {ListNode} l1\n * @param {ListNode} l2\n * @return {ListNode}\n */\nvar addTwoNumbers = function(l1, l2) {\n    var la,le; // 声明头结点和尾结点\n    var carry = 0; // 进位,0表示不进位,1表示进位\n    while(l1 || l2) {\n        val1 = l1 ? l1.val : 0;\n        val2 = l2 ? l2.val : 0;\n        var sum = val1 + val2 + carry;\n        if(sum > 9) {\n            sum = sum % 10;\n            carry = 1;\n        } else {\n            carry = 0;\n        }\n\n        if(le) {\n            le.next = new ListNode(sum);\n            le = le.next;\n        } else {\n            la = new ListNode(sum);\n            le = la;\n        }\n\n        if(l1) {\n            l1 = l1.next;\n        }\n        if(l2) {\n            l2 = l2.next;\n        }\n    }\n\n    if(carry == 1) {\n        le.next = new ListNode(carry);\n    }\n\n    return la;\n};\n```\n## SCALA解法\n```scala\n/**\n * Definition for singly-linked list.\n * class ListNode(var _x: Int = 0) {\n *   var next: ListNode = null\n *   var x: Int = _x\n * }\n */\nobject Solution {\n    def addTwoNumbers(l1: ListNode, l2: ListNode): ListNode = {\n        var l11 = l1;\n        var l22 = l2;\n        var la : ListNode = null; // 头结点\n        var le : ListNode = new ListNode(); // 临时存储结点,最后为尾结点;\n        var carry : Int = 0; // 进制,0表示不用进制,1表示需要进\n\n        while (l11 != null || l22 != null) {\n            var val1 : Int = if(l11 != null) l11.x else 0;\n            var val2 : Int = if(l22 != null) l22.x else 0;\n            var sum : Int = val1 + val2 + carry;\n            if(sum > 9) {\n                sum = sum % 10;\n                carry = 1;\n            } else {\n                carry = 0;\n            }\n\n            if(la == null) {\n                la = new ListNode(sum);\n                le = la;\n            } else {\n                le.next = new ListNode(sum);\n                le = le.next;\n            }\n\n            if(l11 != null) {\n                l11 = l11.next;\n            }\n\n            if(l22 != null) {\n                l22 = l22.next;\n            }\n        }\n        if(carry == 1) {\n            le.next = new ListNode(carry);\n        }\n\n        return la;\n    }\n}\n```','2021-02-22 03:28:40.818000','LeetCode算法练习之两数相加','https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2505295436,1963809141&fm=26&gp=0.jpg','原创',_binary '',_binary '\0',_binary '','两数相加','2021-02-22 03:37:03.324000',7,34,1),(10,_binary '\0',_binary '','<h1 style=\"text-align:center;\">无重复字符的最长子串</h1>\n\n## 内容\n\n	给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。\n\n## 示例\n\n	输入: s = \"abcabcbb\"\n	输出: 3 \n	解释: 因为无重复字符的最长子串是 \"abc\"，所以其长度为 3。\n\n## JAVA解法 1\n\n```java\n/*\n * @lc app=leetcode.cn id=3 lang=scala\n *\n * [3] 无重复字符的最长子串\n */\n\n// @lc code=star\nclass Solution {\n    public int lengthOfLongestSubstring(String s) {\n        int length = 0;\n        int i      = 0;\n        while(i < s.length()) {\n            char tmp = s.charAt(i);\n            int l = 1;\n            for(int j = i+1; j < s.length(); j++) {\n                if(tmp == s.charAt(j)) {\n                    break;\n                } else {\n                    boolean ret = false;\n                    for(int k = i + 1; k < j; k++) {\n                        if(s.charAt(k) == s.charAt(j)) {\n                            ret = true;\n                            break;\n                        }\n                    }\n                    if(ret) {\n                        break;\n                    }\n                }\n\n                l++;\n            }\n            length = length < l ? l : length;\n            i++;\n        }\n\n        return length;\n    }\n}\n// @lc code=end\n```\n\n## JAVA解法2\n\n```java\n/*\n * @lc app=leetcode.cn id=3 lang=java\n *\n * [3] 无重复字符的最长子串\n */\n\n// @lc code=start\nclass Solution {\n    public int lengthOfLongestSubstring(String s) {\n        int length = s.length();\n        int result = 0;\n        Map<Character, Integer> map = new HashMap<>();\n        for(int j = 0, i = 0; j < length; j++) {\n            if(map.containsKey(s.charAt(j))) {\n                i = Math.max(map.get(s.charAt(j)), i);\n            }\n            result = Math.max(result, j - i + 1); \n            map.put(s.charAt(j), j+1);\n        }\n        return result;\n    }\n}\n// @lc code=end\n```\n\n## C解法\n```c\n/*\n * @lc app=leetcode.cn id=3 lang=c\n *\n * [3] 无重复字符的最长子串\n */\n\n// @lc code=start\n\n\nint lengthOfLongestSubstring(char * s){\n    int length = strlen(s);\n    int result = 0;\n    for(int i = 0; i< length; i++) {\n        char c = s[i];\n        int le = 1;\n        for(int j = i + 1; j < length; j++) {\n            if(c == s[j]) {\n                break;\n            } else {\n                int ret = 0;\n                for(int k = i + 1; k < j; k++) {\n                    if(s[k] == s[j]) {\n                        ret = 1;\n                        break;\n                    }\n                }\n                if(ret == 1) {\n                    break;\n                }\n            }\n\n            le++;\n        }\n        result = result < le ? le : result; \n    }\n\n    return result;\n}\n// @lc code=en\n```\n## JavaScript解法\n\n```javascript\n/*\n * @lc app=leetcode.cn id=3 lang=javascript\n *\n * [3] 无重复字符的最长子串\n */\n\n// @lc code=start\n/**\n * @param {string} s\n * @return {number}\n */\nvar lengthOfLongestSubstring = function(s) {\n    var map = new Map();\n    var length = s.length;i\n    var result = 0;\n    var tempIndex = 0;i\n    for(var i = 0 ; i < length; i++) {\n        if(map.has(s[i])) {\n            tempIndex = Math.max(tempIndex, map.get(s[i]));\n        }\n        result = Math.max(result, i - tempIndex + 1);\n        map.set(s[i], i + 1);\n    }\n\n    return result;\n};\n// @lc code=end\n```\n\n## SCALA解法\n\n```scala\n/*\n * @lc app=leetcode.cn id=3 lang=scala\n *\n * [3] 无重复字符的最长子串\n */\n\n// @lc code=start\nobject Solution {\n    def lengthOfLongestSubstring(s: String): Int = {\n        var length : Int            = s.length();\n        var map    : Map[Char, Int] = Map();\n        var i      : Int            = 0;\n        var index  : Int            = 0;\n        var result : Int            = 0;\n        for(i <- 0 to length - 1) {\n            if(map.contains(s.charAt(i))) {\n                index = Math.max(index, map.get(s.charAt(i)).get);\n            }\n            result = Math.max(result, i - index + 1);\n            var num : Int = i + 1;\n            map+=(s.charAt(i) -> num);\n        }\n\n        return result;\n    }\n}\n// @lc code=en\n```','2021-02-24 05:29:23.384000','LeetCode算法练习之无重复字符的最长子串','https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2505295436,1963809141&fm=26&gp=0.jpg','原创',_binary '',_binary '\0',_binary '','无重复字符的最长子串','2021-02-24 05:30:07.100000',2,34,1),(11,_binary '\0',_binary '','# 目的\n**  实现对Spirngboot应用程序中业务日已累计的日志通过Kafka收集到ELk中进行日志的实时和快速的查询,\n   Kafka是由Apache软件基金会开发的一个开源流处理平台，由Scala和Java编写。Kafka是一种高吞吐量的分布式发布订阅消息系统，它可以处理消费者在网站中的所有动作流数据\n   ELK是ElasticSearch+Logstash+Kibana的简称.**\n***   			Elasticsearch是个开源分布式搜索引擎，它的特点有：分布式，零配置，自动发现，索引自动分片，索引副本机制，restful风格接口，多数据源，自动搜索负载等。\n			Logstash是一个完全开源的工具，他可以对你的日志进行收集、过滤，并将其存储供以后使用\n			Kibana 也是一个开源和免费的工具，它Kibana可以为 Logstash 和 ElasticSearch 提供的日志分析友好的 Web 界面，可以帮助您汇总、分析和搜索重要数据日志*\n# Docker搭建ELK环境\n## 安装Elasticsearch\n### 搜索镜像\n	docker search elasticsearch\n![](http://huanglin.online/photo/group1/M00/00/00/rBsQBGBAZ3qAAarNAAdZD5DYez0752.png)\n### 拉取镜像\n	这里选择根据下载数最多的选择docker.io/elasticsearch镜像,下载镜像是需要选择好镜像版本,\n	这里选择7.5.0,后面的Logstash和Kibana需要选择相同的版本好.\n	docker pull docker.io/elasticsearch:7.5.0\n### 安装镜像\n	docker run --name es -d -p 9300:9300 -p 9200:9200 -e \\\n	\"discovery.type=single-node\" docker.io/elasticsearch:7.5.0\n	# --name es 给安装的镜像起名字\n	# -d 表示后台运行\n	# -p xxx:xxxx表示把镜像中的端口给映射出来\n	# -e \"discovery.type=single-node\" 表示单机模式\n	# docker.io/elasticsearch:7.5.0 之前下载好的镜像\n### 安装测试\n	访问: localhost:9200出现下图表示安装成功\n![](http://huanglin.online/photo/group1/M00/00/00/rBsQBGBAhl-AHKIhAABm9MMKRgA486.png)\n## 安装Logstash\n### 搜索镜像\n	docker search logstash\n![](http://huanglin.online/photo/group1/M00/00/00/rBsQBGBAh8-ACS3iAAa3BrCaBl8965.png)\n### 拉取镜像\n	这里选择根据下载数最多的选择docker.io/logstash镜像,下载镜像是需要选择好镜像版本,\n	这里选择7.5.0版本需要和elasticsearch版本保持一致\n	sudo docker pull docker.io/logstash:7.5.0\n### 安装镜像\n	docker run -d --name es_logstash docker.io/logstash:7.5.0\n	# -d 表示镜像后台运行\n	# --name es_logstash 表示给该镜像起的名字\n	# docker.io/logstash:7.5.0 之前下载好的镜像\n### 修改配置\n	 进入logstash镜像中\n	 docker exec -it es_logstash /bin/bash\n	 进入到congfig目录下修改logstash.yml文件\n	 vi config/logstash.yml\n	 修改logstash.yml里面的\n	 xpack.monitoring.elasticsearch.hosts: [ \"http://elasticsearch:9200\" ]\n	 把localhost修改为安装elasticsearch的机器的公网IP地址或虚拟局域网网络IP,我修改如下:\n	  xpack.monitoring.elasticsearch.hosts: [ \"http://172.17.0.1:9200\" ]\n### 重启Logstash\n	docker restart es_logstash\n## 安装Kibana\n### 搜索镜像\n	docker search kibana\n![](http://huanglin.online/photo/group1/M00/00/00/rBsQBGBAjO2AKQgQAAbb6TrN2Es166.png)\n### 拉取镜像\n	这里选择根据下载数最多的选择docker.io/kibana镜像,下载镜像是需要选择好镜像版本,\n	这里选择7.5.0版本需要和elasticsearch版本保持一致\n	docker pull docker.io/kibana:7.5.0\n### 安装镜像\n	docker run --name es_kibana -p 5601:5601 -d \\\n	-e ELASTICSEARCH_URL=http://172.17.0.1:9200 docker.io/kibana:7.5.0\n	# -d 表示镜像后台运行\n	# -p 5601:5061 表示把镜像中端口映射出来便于外界访问\n	# -- name es_kibana 表示给该镜像起的名字\n	# -e ELASTICSEARCH_URL=http://172.17.0.1:9200 表示链接elasticsearch的地址,\n	注意\"172.17.0.1\"是elasticsearch的机器的公网IP地址或虚拟局域网网络IP\n	# docker.io/kibana:7.5.0 之前下载好的镜像\n### 修改配置\n	进入Kibana镜像\n	docker exec -it es_kibana /bin/bash\n	修改kibana.yml配置文件\n	vi config/kibana.yml\n	把elasticsearch.hosts: [ \"http://localhost:9200\"]改为elasticsearch.hosts: [ \"http://172.17.0.1:9200\"]\n	设置中文,在文件最后加上如下配置:\n	i18n.locale: \"zh-CN\" \n### 重启Kibana\n	docker restart es_kibana\n### 访问\n	http://localhost:5601/ 出现如下界面表示安装成功\n![](http://huanglin.online/photo/group1/M00/00/00/rBsQBGBAkl2AAHqzAAKK2EWt4Yc167.png)\n# Docker搭建Kafka环境\n## 安装zookeeper\n### 拉取镜像\n	下载最新的版本即可\n	 docker pull  zookeeper:latest\n### 安装镜像\n	docker run -d -p 2181:2181 --name zookeeper zookeeper:latest\n	# -d 表示镜像后台运行\n	# -p 2181:2181 表示把镜像中端口映射出来便于外界访问\n	# -- zookeeper 表示给该镜像起的名字\n	# zookeeper:latest 之前下载好的镜像\n## 安装Kafka\n### 拉取镜像\n	下载最新的版本即可\n	docker pull wurstmeister/kafka:latest\n### 安装镜像\n	docker run -d --name kafka -p 9092:9092  -e KAFKA_BROKER_ID=0 \\\n	-e KAFKA_ZOOKEEPER_CONNECT=172.17.0.1:2181 \\\n	-e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://172.17.0.1:9092 \\\n	-e KAFKA_LISTENERS=PLAINTEXT://0.0.0.0:9092 -t wurstmeister/kafka:latest\n	# -e KAFKA_BROKER_ID=0  在kafka集群中，每个kafka都有一个BROKER_ID来区分自己\n	# -e KAFKA_ZOOKEEPER_CONNECT=172.17.0.1:2181/kafka 配置zookeeper管理kafka的路径172.17.0.1:2181/kafka\n	# -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://172.17.0.1:9092  \n	把kafka的地址端口注册给zookeeper，如果是远程访问要改成外网IP,类如Java程序访问出现无法连接。\n	# -e KAFKA_LISTENERS=PLAINTEXT://0.0.0.0:9092 配置kafka的监听端口\n	注意\"172.17.0.1\"是elasticsearch的机器的公网IP地址或虚拟局域网网络IP\n# 配置Kafka和Logstash的数据链接\n## 创建Kafka主题用于接受Springboot应用产生的业务数据\n	进入Kafka镜像\n	docker exec -it kafka /bin/bash\n	创建日志主题 myBlogLog\n	kafka-topics.sh --create --zookeeper 172.17.0.1:2181 \\\n	--replication-factor 1  --partitions 3 --topic myBlogLog\n	查看主题列表主题是否创建成功\n	kafka-topics.sh --list --zookeeper 172.17.0.1:2181\n## 配置Logstash中的数据收集源为Kafka的myBlofLog主题\n	进入Logstash镜像\n	修改logstash.conf文件\n	vi /usr/share/logstash/pipeline/logstash.conf\n	修改该 logstash.conf的内容\n	input {\n		  kafka {\n			id => \"blog\"\n			bootstrap_servers => \"172.17.0.1:9092\" # kafka地址\n			topics => [\"myBlogLog\"]    # 日志主题\n			auto_offset_reset => \"latest\" \n		  }\n	}\n\n	output {\n	  elasticsearch {\n		hosts => [\"172.17.0.1:9200\"] # es地址\n		index => \"blog_logs\" # 索引名称\n	  }\n	}\n# SpirngBoot整合Kafka进行日志的收集\n## 添加Spirngboot整合Kafka的依赖\n		<dependency>\n			<groupId>org.springframework.kafka</groupId>\n			<artifactId>spring-kafka</artifactId>\n		</dependency>\n## 为application.yml中添加如下信息\n	spring:\n 		kafka:\n			bootstrap-servers:\n    		- 127.0.0.1:9092 # kafka地址\n    		producer:\n      			key-serializer: org.apache.kafka.common.serialization.StringSerializer\n     			 value-serializer: org.apache.kafka.common.serialization.StringSerializer\n## 添加Kafkag工具类型\n```java\n	import java.io.PrintWriter;\n	import java.io.StringWriter;\n	import java.io.Writer;\n\n	import org.springframework.beans.factory.annotation.Autowired;\n	import org.springframework.kafka.core.KafkaTemplate;\n	import org.springframework.stereotype.Component;\n\n	@Component\n	public class KafkaUtil {\n\n    @Autowired\n    private KafkaTemplate<String, String> kafkaTemplate;\n\n    /***\n     * 发送普通日志的方法\n     * @param message\n     * @return\n     */\n    public String sendInfoMessage(String message){\n        try{\n            kafkaTemplate.send(\"myBlogLog\", \"blog\",message);\n        }catch (Exception e){\n            e.printStackTrace();\n            return \"fail\";\n        }\n        return \"success\";\n    }\n\n    /***\n     * 发送异常日志的方法\n     * @param em\n     * @return\n     */\n    public String sendErrorMessage(Exception em){\n        try{\n            Writer writer=new StringWriter();\n            PrintWriter pw=new PrintWriter(writer);\n            em.printStackTrace(pw);\n            kafkaTemplate.send(\"myBlogLog\", \"blog\",writer.toString());\n        }catch (Exception e){\n            e.printStackTrace();\n            return \"fail\";\n        }\n        return \"success\";\n    }\n}\n```\n### 在日志切面处打印日志和向kafka发送日志\n	这里贴出了打印日志和发送日志kafaka的切面编程代码,其他相关类代码没有贴出,可自行修改.\n```java\n/**\n * 用户所有提交请求\n * \n * @author huanglin\n * @Data   2020年2月6日\n */\n@Aspect\n@Component\npublic class LogAspect {\n    private static Logger logger = LoggerFactory.getLogger(LogAspect.class);\n\n    @Autowired\n    RedisUtil redisUtil;\n\n    @Autowired\n    KafkaUtil kafkautil;\n\n    @Pointcut(\"execution(* com.hl.myblog.web..*.*(..))\")\n    public void log() {}\n\n    @Pointcut(\"@annotation(com.hl.myblog.annotation.RecordLog)\")\n    public void recordLog() {}\n\n    @Pointcut(\"@annotation(com.hl.myblog.annotation.AccessLimit)\")\n    public void accessLimit() {}\n\n    @Before(\"log()\")\n    public void doBefore(JoinPoint joinPoint) {\n        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder\n											.getRequestAttributes();\n        HttpServletRequest       request    = attributes.getRequest();\n\n        String     url           = request.getRequestURL().toString();\n        String     ip            = request.getRemoteAddr();\n        String     classMethod   = joinPoint.getSignature().getDeclaringTypeName() + \n		\".\" + joinPoint.getSignature().getName();\n        Object[]   args          = joinPoint.getArgs();\n        RequestLog requestLog    = new RequestLog(url, ip, classMethod, args);\n\n        logger.info(\"\\n\" + \"Request :{}\", requestLog.toString(PrefixString.Indent1()));\n        kafkautil.sendInfoMessage(\"Request : { \" + requestLog.toString(PrefixString.Indent1()) + \"}\");\n    }\n\n    @After(\"log()\")\n    public void doAfter() {}\n\n    @AfterReturning(returning = \"result\", pointcut = \"log()\")\n    public void doAfterReturing(Object result) {\n        logger.info(\"\\n\" + \"Result :\" + \"        \\n \" + \"{}\", result);\n        kafkautil.sendInfoMessage(\"Result : \" + \"{ \" + result + \"}\");\n    }\n\n    private class RequestLog {\n        private String   url;\n        private String   ip;\n        private String   classMethod;\n        private Object[] args;\n\n        public RequestLog(String url, String ip, String classMethod, Object[] args) {\n            super();\n            this.url = url;\n            this.ip = ip;\n            this.classMethod = classMethod;\n            this.args = args;\n        }\n\n        public String toString(String prefix) {\n            StringBuilder sb = new StringBuilder();\n\n            sb.append(\"\\n\");\n            sb.append(prefix + \"url         = \" + url + \"\\n\");\n            sb.append(prefix + \"ip          = \" + ip + \"\\n\");\n            sb.append(prefix + \"classMethod = \" + classMethod + \"\\n\");\n            sb.append(prefix + \"args        = \" + Arrays.toString(args));\n\n            return sb.toString();\n        }\n    }\n\n    @Around(\"recordLog()\")\n    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {\n        Object res  = null;\n        long   time = System.currentTimeMillis();\n        try {\n            res  = joinPoint.proceed();\n            time = System.currentTimeMillis() - time;\n\n            return res;\n        } finally {\n            try {\n                // 方法执行完后增加日志\n                addRecordLog(joinPoint, res, time);\n            } catch (Exception e) {\n                logger.info(\"\\n\" + \"LogAspect 操作失败: \" + e.toString());\n                kafkautil.sendErrorMessage(e);\n            }\n        }\n    }\n\n    private void addRecordLog(JoinPoint joinPoint, Object res, long time) \n											throws JsonProcessingException {\n        MethodSignature signature    = (MethodSignature)joinPoint.getSignature();\n        RecordLogObj    recordLogObj = new RecordLogObj();\n        ObjectMapper    objectMapper = new ObjectMapper();\n        recordLogObj.setRunTime(time);\n        recordLogObj.setReturnValue(objectMapper.writeValueAsString(res));\n        recordLogObj.setArgs(objectMapper.writeValueAsString(joinPoint.getArgs()));\n        recordLogObj.setCreateTime(new Date());\n        recordLogObj.setMethod(signature.getDeclaringTypeName() \n		+ \".\" + signature.getName());\n        recordLogObj.setUserName(getUserName());\n        RecordLog recordLog = signature.getMethod().getAnnotation(RecordLog.class);\n        if(recordLog != null) {\n            recordLogObj.setDescribe(getDetail(signature.getParameterNames(), \n														joinPoint.getArgs(), recordLog));\n            recordLogObj.setRecordType(recordLog.recordType().getValue());\n            recordLogObj.setRecoredObject(recordLog.recordObject().getValue());\n        } else {\n            logger.info(\"recordLog is null\");\n            kafkautil.sendInfoMessage(\"recordLog is null\");\n        }\n\n        logger.info(recordLogObj.toString(PrefixString.Indent1()));\n        kafkautil.sendInfoMessage(\"recordLong: { \" + recordLogObj.toString(PrefixString.Indent1()));\n    }\n\n    private String getUserName() {\n        ServletRequestAttributes attributes  = (ServletRequestAttributes) RequestContextHolder\n											.getRequestAttributes();\n        HttpServletRequest       request     = attributes.getRequest();\n        String                   token       = request.getHeader(\"Authorization\");\n        String                   userName    = \"游客\";\n        if(token != null && !\"\".equals(token)) {\n            userName    = JWTUtil.getUserName(token);\n        }\n\n        return userName;\n    }\n\n    /**\n     * 对当前登录用户和占位符处理\n     * @param  argNames 方法参数名称数组\n     * @param  args 方法参数数组\n     * @param  annotation 注解信息\n     * @return 返回处理后的描述\n     */\n    private String getDetail(String[] argNames, Object[] args, RecordLog annotation){\n\n        Map<Object, Object> map = new HashMap<>(4);\n        for(int i = 0;i < argNames.length;i++){\n            map.put(argNames[i],args[i]);\n        }\n\n        String       detail       = annotation.detail();\n        ObjectMapper objectMapper = new ObjectMapper();\n        try {\n            detail = \"\" + annotation.detail();\n            for (Map.Entry<Object, Object> entry : map.entrySet()) {\n                Object k = entry.getKey();\n                Object v = entry.getValue();\n                detail = detail.replace(\"{{\" + k + \"}}\", objectMapper.writeValueAsString(v));\n            }\n        }catch (Exception e){\n            e.printStackTrace();\n        }\n        return detail;\n    }\n\n    @Before(\"accessLimit()\")\n    public void doAccessLinit(JoinPoint joinPoint) throws Exception {\n        // 获取注解accessLimit信息\n        MethodSignature signature  = (MethodSignature)joinPoint.getSignature();\n        AccessLimit     annotation = signature.getMethod().getAnnotation(AccessLimit.class);\n        // 获取request对象\n        ServletRequestAttributes attributes  = (ServletRequestAttributes) RequestContextHolder\n											.getRequestAttributes();\n        HttpServletRequest       request     = attributes.getRequest();\n        String                   classMethod = joinPoint.getSignature().getDeclaringTypeName() + \".\" \n		+ joinPoint.getSignature().getName();\n\n        int seconds  = annotation.seconds();\n        int maxCount = annotation.maxCount();\n        String ip = request.getHeader(\"x-forwarded-for\");// 有可能ip是代理的\n        if(ip ==null || ip.length() ==0 || \"unknown\".equalsIgnoreCase(ip)) {\n            ip = request.getHeader(\"Proxy-Client-IP\");\n        }      \n        if(ip ==null || ip.length() ==0 || \"unknown\".equalsIgnoreCase(ip)) {\n            ip = request.getHeader(\"WL-Proxy-Client-IP\");\n        }      \n        if(ip ==null || ip.length() ==0 || \"unknown\".equalsIgnoreCase(ip)) {\n             ip = request.getRemoteAddr();\n        } \n\n        Integer count = (Integer)redisUtil.get(classMethod + ip);\n        if(count == null){\n            //第一次访问\n            redisUtil.set(classMethod + ip, count);\n            redisUtil.expire(classMethod + ip, seconds);\n        }else if(count < maxCount){\n            //加1\n            if(redisUtil.getExpire(classMethod + ip) == -1) {\n                count = 0;\n            } else {\n                count = count + 1;\n            }\n            redisUtil.incr(classMethod + ip, count);\n        }else{\n            //超出访问次数\n            logger.info(\"访问过快ip  ===> \" + ip + \" 且在   \" + seconds \n			+ \" 秒内超过最大限制  ===> \" + maxCount + \" 次数达到    ====> \" + count);\n            kafkautil.sendInfoMessage(\"访问过快ip  ===> \" + ip + \" 且在   \" + seconds\n			+ \" 秒内超过最大限制  ===> \" + maxCount + \" 次数达到    ====> \" + count);\n            throw new AccessLimitException(seconds + \"秒内超过最大限制\" + maxCount + \"次数达到\" + count);\n        }\n    }\n}\n```\n# 通过Kibana查看日志\n## 创建索引模式\n	进入Kibabna中的management进行创建\n![](http://huanglin.online/photo/group1/M00/00/00/rBsQBGBAr7eAOmSFAAFvvCE0Y4M031.png)\n## 通过Discover进行日志的查看\n![](http://huanglin.online/photo/group1/M00/00/00/rBsQBGBAr_aAd9A3AAJsF0ySvq0882.png)','2021-03-03 16:52:52.821000','使用Kafka+ELk实现本博客的应用日志的收集,快速检索和查看',NULL,'原创',_binary '',_binary '',_binary '','SpringBoot+Kafka+ELk实现应用日志的收集','2021-03-04 10:14:16.721000',11,33,1);
/*!40000 ALTER TABLE `t_blog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_blog_tags`
--

DROP TABLE IF EXISTS `t_blog_tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_blog_tags` (
  `blogs_id` bigint(20) NOT NULL,
  `tags_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_blog_tags`
--

LOCK TABLES `t_blog_tags` WRITE;
/*!40000 ALTER TABLE `t_blog_tags` DISABLE KEYS */;
INSERT INTO `t_blog_tags` VALUES (2,20),(3,21),(9,21),(10,21),(11,25),(11,24),(11,23),(11,22);
/*!40000 ALTER TABLE `t_blog_tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_comment`
--

DROP TABLE IF EXISTS `t_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_comment` bit(1) NOT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `blog_id` bigint(20) DEFAULT NULL,
  `parent_comment_id` bigint(20) DEFAULT NULL,
  `create_time` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_comment`
--

LOCK TABLES `t_comment` WRITE;
/*!40000 ALTER TABLE `t_comment` DISABLE KEYS */;
INSERT INTO `t_comment` VALUES (42,_binary '','http://ppe.oss-cn-shenzhen.aliyuncs.com/collections/182/4/thumb.jpg','dasdasd','www.huanglin@qq.com','huanglin',8,NULL,'2021-01-31 17:18:04.965000'),(43,_binary '','http://ppe.oss-cn-shenzhen.aliyuncs.com/collections/182/4/thumb.jpg','dsadasda','www.huang@qq.com','huanglin',8,42,'2021-01-31 17:18:19.838000');
/*!40000 ALTER TABLE `t_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_tag`
--

DROP TABLE IF EXISTS `t_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_tag`
--

LOCK TABLES `t_tag` WRITE;
/*!40000 ALTER TABLE `t_tag` DISABLE KEYS */;
INSERT INTO `t_tag` VALUES (20,'分享'),(21,'算法练习'),(22,'MQ'),(23,'ETL'),(24,'Docker'),(25,'SpringBoot');
/*!40000 ALTER TABLE `t_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_type`
--

DROP TABLE IF EXISTS `t_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_type`
--

LOCK TABLES `t_type` WRITE;
/*!40000 ALTER TABLE `t_type` DISABLE KEYS */;
INSERT INTO `t_type` VALUES (30,'JAVASCRIPT'),(32,'JAVA'),(33,'分享'),(34,'LeetCode');
/*!40000 ALTER TABLE `t_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) DEFAULT NULL,
  `crate_time` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (1,'http://ppe.oss-cn-shenzhen.aliyuncs.com/collections/182/4/thumb.jpg',NULL,NULL,'huanglin','60efcf79ceb712a85534fe42ac684896',NULL,NULL,'hl');
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-01 12:48:29
