# ZhihuSpider
爬取知乎问题答案的所有图片并下载


#### 抓取的原理

想办法获取图片的链接，然后添加到一个容器中，依次下载，很简单的

#### 过程

一开始我单纯的认为只要获取一个问答首页的源码，然后用正则表达式拿到图片链接，接下来就不是事儿了。但是这样做只能获取到第一页的数据

> 下图为第一页的数据，每一个List-item就是一个回答，这儿一共才20条

![](http://ovxv402e0.bkt.clouddn.com/blog/imgs/ListItem.JPG)

一开始我还不知道原因所在，现在想想自己真笨，然后我点击查看更多回答。```F12```中打开```network```选项卡，再选择```XHR```查看AJAX请求，发现有一个链接特别长，查看```request```和```response```,就发现了下图中的东西

![](http://ovxv402e0.bkt.clouddn.com/blog/imgs/XHR.JPG)

这个请求返回的是一个json数据

![](http://ovxv402e0.bkt.clouddn.com/blog/imgs/data_json.JPG)

这是一个实例链接：```https://www.zhihu.com/api/v4/questions/38906616/answers?include=data%5B*%5D.is_normal%2Cadmin_closed_comment%2Creward_info%2Cis_collapsed%2Cannotation_action%2Cannotation_detail%2Ccollapse_reason%2Cis_sticky%2Ccollapsed_by%2Csuggest_edit%2Ccomment_count%2Ccan_comment%2Ccontent%2Ceditable_content%2Cvoteup_count%2Creshipment_settings%2Ccomment_permission%2Ccreated_time%2Cupdated_time%2Creview_info%2Cquestion%2Cexcerpt%2Crelationship.is_authorized%2Cis_author%2Cvoting%2Cis_thanked%2Cis_nothelp%2Cupvoted_followees%3Bdata%5B*%5D.mark_infos%5B*%5D.url%3Bdata%5B*%5D.author.follower_count%2Cbadge%5B%3F(type%3Dbest_answerer)%5D.topics&offset=3&limit=20&sort_by=default```

链接参数中的offset表示与第一个回答的偏移量，limit表示返回回答数据的条数

我以为这样就可以了，用在线get工具测试了一下，看是不是相同的结果



![](http://ovxv402e0.bkt.clouddn.com/blog/imgs/responsewithoutauth.JPG)

结果响应了这样的json数据，根据那个英语，猜测是没有授权，于是去浏览器开发人员工具下寻找有没有什么特殊```request```的```Header```没有添加，于是发现了一个```authorization```的字段，故添加好header参数后再次尝试，结果呢？这次就对了

![](http://ovxv402e0.bkt.clouddn.com/blog/imgs/getwithauth.JPG)

![](http://ovxv402e0.bkt.clouddn.com/blog/imgs/responsewithauth.JPG)

如果问这个```authorization```字段的值怎么来的，其实我也不知道，我是复制的浏览器的数据

现在拿到了回答的json数据

![](http://ovxv402e0.bkt.clouddn.com/blog/imgs/data_json.JPG)

经过分析，每一个回答的json中有一个content属性，这个属性包含了回答的内容，其中就含有图片的链接。到此，感觉寄几终于找到了光明

![](http://ovxv402e0.bkt.clouddn.com/blog/imgs/content.JPG)

通过这个属性的值，就可以拿到图片的链接。

返回的这个json数据中还包含回答的总数等内容，更多有用的就自己去挖掘了哟
 
#### 使用方法

![](http://ovxv402e0.bkt.clouddn.com/blog/imgs/zhihuspiderMain.JPG)
 
如图 ，修改main方法中的questionId，修改图片保存的路径，注意路径一定要存在，如果没问题的话就能下载图片了
 
#### 注意的地方：

> 此程序不能抓取需要登陆才能查看的问题，如果这个问题需登陆权限，就会返回不正确的数据从而造成程序抛出异常

> 这个项目的设计不是很合理，也没有花时间整理，只是希望能够提供一种思路
