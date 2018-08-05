# SpannableString
解决使用SpannableString时出现的问题

1、默认情况下，点击 ClickableSpan 的文本时会同时触发绑定在 TextView 的监听事件；

2、默认情况下，点击 ClickableSpan 的文本之外的文本时，TextView 会消费该事件，而不会传递给父 View；

3、固定 TextView 行数的时候，点击 ClickableSpan 文本会出现滚动现象；

4、Spannable 与 Ellispsize 同时使用时，Ellipsize 失效；

5、折行策略不尽人意；

> 这里只是对前3个问题进行解决，第四个问题建议是自己计算行数，第五个问题有解决的希望可以分享一下
