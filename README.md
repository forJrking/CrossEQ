# CrossEQ 均衡器（自定义控件）

### 效果图（点赞，支持下开源）
***
![png1.png](https://github.com/forJrking/CrossEQ/blob/master/11.gif)


### 二次开发注意事项
        protected void onDraw(Canvas canvas) {
            ........
            //如果要加入图片使用 drawBitmap()
            canvas.drawCircle(cp.x, cp.y, 15f, paint);//绘制中心点
        }

	int sen = 50;//灵敏度  越大滑动点触控识别度越高
	crossView.sen = 50;//可以这样设置

初始化显示位置如果需要在中间 调用 init(-1,-1)  恢复初始化。

获取当前点的左边 getEQ()返回一个数组 X= getEQ()[0];Y=  getEQ()[1].

如果要做回显需要存储 X，Y的值，使用init(view,x,y)传入即可.

## 联系作者 Jrking
邮箱：forjrking@sina.com
