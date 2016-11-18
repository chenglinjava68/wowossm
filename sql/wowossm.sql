drop table userInfo;

--会员信息表
create table userInfo(
	usid int primary key, --会员编号
	email varchar2(100) unique, --邮箱
	uname varchar2(100), --会员名
	pwd varchar2(100), --密码
	tel varchar2(15), --联系方式
	province varchar2(40), --省份
	city varchar2(50), --城市
	area varchar2(50), --地区
	photo clob,  --用户图像地址
	money number(10,2),  --余额
	udate date,  --注册时间
	point int,  --积分
	status int, --状态(0.未激活  1.正常帐号  2.   3.)
	temp varchar2(200),  --备用字段
	flag varchar2(1000)  --备用字段
);
create sequence seq_usid start with 10001 increment by 1;

--帐号异常记录
create table accountInfo(
	aid int primary key, --编号
	usid int
		constraint FK_userInfo_accountInfo_usid references userInfo(usid),
	adate date,  --异常时间
	edate date,  --结束时间
	content varchar2(2000), --提示信息
	status int, --状态
	temp varchar2(200),  --备用字段
	flag varchar2(1000)  --备用字段
);
create sequence seq_aid start with 10001 increment by 1;

--管理员信息表
create table adminInfo(
	aid int primary key, --编号
	aname varchar2(100), --名字
	pwd varchar2(20), --密码
	status int --状态
);
create sequence seq_afid start with 10001 increment by 1;

insert into adminInfo values(seq_afid.nextval,'a','1234',1);
insert into adminInfo values(seq_afid.nextval,'navy','1234',1);

insert into adminInfo values(seq_afid.nextval,'yc','1234',1);
insert into adminInfo values(seq_afid.nextval,'b','1234',1);

--团购类型
create table typeInfo(
	tid int primary key,  --编号
	tname varchar2(40), --名称
	intro varchar2(200),  --介绍
	status int --状态
);
create sequence seq_tid start with 10001 increment by 1;

select gid,gname,adder,tel,falg,tname as temp from tyepInfo t,goodsInfo g where t.tid=g.tid

--商品信息
create table goodsInfo(
	gid int primary key, --商品编号
	gname varchar2(200), --商品名称
	tid int
		constraint FK_typeInfo_goodsInfo_tid references typeInfo(tid),
	addr varchar2(200), --店铺地址
	tel varchar2(20), --联系方式
	temp varchar2(200),  --备用字段
	flag varchar2(1000)  --备用字段	
);
create sequence seq_gid start with 10001 increment by 1;

--添加一个新列
alter table goodsInfo add tid int;
--给新列添加一个外检约束
alter table goodsInfo add constraint FK_typeInfo_goodsInfo_tid foreign key(tid) references typeInfo(tid); [on delete cascade|on delete set null]


--团购信息
create table tuanInfo(
	tid int primary key, --团购信息编号
	gid int
		constraint FK_goodsInfo_tuanInfo_gid references goodsInfo(gid),
	des varchar2(1000),  --商品描述
	price number(10,2), --原价
	act number(4,2), --折扣
	sdate date, --活动开始时间
	edate date, --活动结束时间
	tip clob, --温馨提示
	content varchar2(2000), --特别提醒
	intr clob, --商品介绍
	total int, --点击次数
	pic varchar2(2000), --商品图片
	temp varchar2(200),  --备用字段
	flag varchar2(1000)  --备用字段
);
create sequence seq_tsid start with 10001 increment by 1;

--套餐内容
create table taoCan(
	cid int primary key,
	tid int
		constraint FK_tuanInfo_taoCan_tid references tuanInfo(tid),
	content varchar2(400), --套餐内容
	des varchar2(100) --数量/规格
);
create sequence seq_cid start with 10001 increment by 1;

--订单表
create table orderInfo(
	oid int primary key,
	cid int
		constraint FK_taoCan_orderInfo_cid references taoCan(cid),
	wprice number(10,2), --购买价
	nums int --购买数量
);
create sequence seq_oid start with 10001 increment by 1;


select * from (select a.*,rownum rn from (select usid,email,uname,pwd,tel,province,city,area,photo,money,udate,point,status,temp,flag from userInfo order by usid)a where rownum<=10) b where rn>5


update userInfo set photo=nvl(photo,''))


select tf.*,gname from tuanInfo tf,goodsInfo gf where tf.gid=gf.gid;

select * from adminInfo;

select * from goodsInfo;

update goodsInfo set addr='湖南衡阳' 

delete from tuanInfo



select tf.*,gf.gname from tuanInfo tf,goodsInfo gf where gf.tid=tf.tid order by gid



select gf.*,tname from goodsInfo gf,typeInfo tf where gf.tid=tf.tid order by gid

select * from (select a.*,rownum rn from(select tf.tid,tf.gid,price,act,sdate,edate,des,total,pic,gname temp from tuanInfo tf,goodsInfo gf where tf.gid=gf.gid order by tf.tid)a where rownum<=10)b where rn>0;