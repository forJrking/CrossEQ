# CrossEQ 均衡器（自定义控件）

### 效果图
***
![png1.png](https://github.com/forJrking/CrossEQ/blob/master/11.gif)


### 二次开发注意事项
        protected void onDraw(Canvas canvas) {
            ........

            canvas.drawCircle(cp.x, cp.y, 15f, paint);//绘制中心点
            //如果要加入图片使用 drawBitmap()
        }

		int sen = 50;//灵敏度的调节 越大滑动点触控识别度越高
		crossView.sen = ...;

初始化显示位置如果需要再中间 init(view,-1,-1)即可
如果要做回显需要存储 X，Y的值，使用init(view,x,y)传入即可
