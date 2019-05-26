# redis service
redis操作服务化, 这一部分是用StringRedisTemplate来完成的，可以省去序列化配置
## 注意
禁止线上使用keys命令，keys会阻塞服务器，对于单线程的redis来说，会严重影响服务器的性能。对于2.8以上的版本，redis提供了一种更好的遍历key的命令：scan
```bash
SCAN cursor [MATCH pattern] [COUNT count]
```
- SCAN 每次执行都只会返回少量元素，所以可以用于生产环境，而不会出现像 KEYS 或者 SMEMBERS 命令带来的可能会阻塞服务器的问题。
- SCAN命令是一个基于游标的迭代器。这意味着命令每次被调用都需要使用上一次这个调用返回的游标作为该次调用的游标参数，以此来延续之前的迭代过程
- 当SCAN命令的游标参数（即cursor）被设置为 0 时， 服务器将开始一次新的迭代， 而当服务器向用户返回值为 0 的游标时， 表示迭代已结束。

[scan命令参考](http://doc.redisfans.com/key/scan.html)