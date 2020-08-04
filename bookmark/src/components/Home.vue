<template>
    <div class="home">
        <div class="header">
            <div class="login_info">
				<div>欢迎光临网上书城</div>
				<div class="login_status" v-if="!user" @click="loginAction">请登录</div>
				<div class="login_status" @click="logAction" v-if="user">{{user.phone}}</div>
			</div>
        </div>

		<div class="header_handle">
			<div class="search_box">
				<el-input class="search_input" v-model="inputValue" placeholder="请输入内容"></el-input>
				<el-button type="success" @click="searchBooks" v-if="!isSearch">搜索图书</el-button>
				<el-button type="primary" @click="cancleSearch" v-if="isSearch">取消搜索</el-button>
			</div>
			<div class="mine_box">
				
				<el-button class="shop_btn" type="primary" @click="goShop">购物车</el-button>
				<div class="shop_count">共<span class="shop_count_value">{{shopCount}}</span> 件商品</div>
  				<!-- <el-button type="success">我的订单</el-button> -->
			</div>
		</div>

        <div class="category" v-if="!isSearch">
            <div v-for="(item,index) in category" :key="item.id"  :class="index == selectCatIndex ? 'active':'category_item'" @click="selectCategory(item,index)">
                {{item.title}}
            </div>

        </div>

        <div class="bookContent">

            <div class="book_box" v-for="(book,index) in books" :key="index">
                <img class="book_img" :src="book.img" alt="">
                <div class="book_title">{{book.title}}</div>
                <div class="book_des">{{book.sub1}}</div>
                <div class="amount_box">
                    <div class="book_price">￥{{book.price}}</div>
                    <div></div>
                </div>
				<div>
					<el-button type="primary" @click="addShop(book)">加入购物车</el-button>
					<el-button type="primary" @click="showDetail(book)">查看详情</el-button>
				</div>
            </div>

        </div>

		<div class="page_content" v-if="!isSearch">
			<el-pagination
			@current-change="handleCurrentChange"
			background
			:page-size="10"
			layout="prev, pager, next"
			:total="totleCount">
			</el-pagination>
		</div>

		<el-dialog
			title="详情"
			:visible.sync="detailDialogVisible"
			width="900px"
			center>
			<div class="dialog_content">
				<div class="detail_header">
					<img class="detail_img" :src="selectBook.img" alt="">
					<div class="detail_content">
						<div class="detail_title">{{selectBook.title}}</div>
						<div class="detail_tags">{{selectBook.tags}}</div>
						<div class="detail_price">￥{{selectBook.price}}</div>
						<div>{{selectBook.sub1}}</div>
						
					</div>
				</div>

				<div class="detail_sub">{{selectBook.sub2}}</div>
			</div>
			<span slot="footer" class="dialog-footer">
				<el-button type="primary" @click="detailAddShop()">加入购物车</el-button>
			</span>
		</el-dialog>

		<el-dialog
		title="退出登录"
			:visible.sync="logoutDialogVisible"
			width="600px"
			center>
			<div v-if="user">是否确认退出当前账号：{{user.phone}}</div>
			<span slot="footer" class="dialog-footer">
				<el-button @click="logoutDialogVisible = false">取 消</el-button>
				<el-button type="primary" @click="logoutAction">确 定</el-button>
			</span>
		</el-dialog>

    </div>

	

</template>



<script>

import router from '@/router';
import qs from 'qs'

export default {
    name: "Home",
    data() {
        return {
            inputValue: '',
            category: [],
			chooseCategory: null,
			selectCatIndex: 0,
			books:[],
			page:1,
			totleCount:0,
			selectBook: {},
			detailDialogVisible: false,
			user: null,
			shop: null,
			shopCount: 0,
			logoutDialogVisible: false,
			isSearch: false
        }
	},
	mounted() {
		var user = JSON.parse(localStorage.getItem('user'));
		this.user = user;
		this.getCategory()
		if (user) {
			this.getShop(false)
		}
	},
    methods: {
		logAction: function() {
			this.logoutDialogVisible = true;
		},
		logoutAction: function() {
			localStorage.setItem('user',null);
			this.user = null;
			this.shop = null;
			this.logoutDialogVisible = false;
			this.shopCount = 0;
		},
        selectCategory: function(item,index) {

			if (index == this.selectCatIndex) {
				return;
			}
			this.selectCatIndex = index;
			this.chooseCategory = item;
			this.getBooks()
		},
		getCategory: function() {
			var that = this;
			this.axios.get('/v1/category/list')
			.then((response) => {
				that.category = response.data.data;
				that.chooseCategory = that.category[0];
				that.selectCatIndex = 0;
				that.getBooks();
			}).catch((response) => {

			})
		},
		cancleSearch: function() {
			this.isSearch = false;
			this.getBooks();
		},
		searchBooks: function() {

			if (this.inputValue.length == 0) {
				this.$message.error("请输入关键字查询");
				return
			}

			var that = this;
			this.axios.get('/v1/book/search',{
				params: {
					keyWords: that.inputValue,
				}
			})
			.then((response) => {
				var list = response.data.data;
				var books = [];
				for (var i = 0; i < list.length; i ++ ) {
					var book = list[i];
					book.price = (book.price / 100).toFixed(2)
					books.push(book)
				}
				that.books = books;
				that.isSearch = true;
				// that.totleCount = response.data.extraData.totleCount;

			}).catch((response) => {

			})
		},
		getShop: function(goShop) {

			if (!this.user) {
				this.loginAction()
				return
			}

			var that = this;
			this.axios.get('/v1/order/list',{
				params: {
					phone: that.user.phone,
					pageSize: 10,
					page: 1
				}
			})
			.then((response) => {
				if (response.data.code == 1000) {
					if (response.data.data.length > 0) {
						that.shop = response.data.data[0]
						that.shop.book_ids = JSON.parse(that.shop.book_ids)
						that.shop.book_count = JSON.parse(that.shop.book_count)
						that.shop.price = (that.shop.price / 100).toFixed(2)
						var allCount = 0;
						for (var i = 0; i < that.shop.book_count.length; i++) {
							var count = that.shop.book_count[i];
							allCount = allCount + count;
						}
						that.shopCount = allCount;
					}
					if (goShop) {
						localStorage.setItem('shop',JSON.stringify(that.shop))
						that.$router.push('Shop')
					}
				}
			}).catch((response) => {

			})

		},
		getBooks: function() {
			var that = this;
			var category = that.chooseCategory.categoryId;
			this.axios.get('/v1/book/list',{
				params: {
					pageSize: 10,
					category: category,
					page: that.page
				}
			})
			.then((response) => {
				var list = response.data.data;
				var books = [];
				for (var i = 0; i < list.length; i ++ ) {
					var book = list[i];
					book.price = (book.price / 100).toFixed(2)
					books.push(book)
				}
				that.books = books;

				that.totleCount = response.data.extraData.totleCount;

			}).catch((response) => {

			})
		},
		handleCurrentChange(val) {
			this.page = val;
			this.getBooks();
		},
		detailAddShop: function() {
			this.addShop(this.selectBook)
		},
		// 添加到购物车
		addShop: function(book) {
			let user = JSON.parse(localStorage.getItem('user'));

			if (user) {

				var that = this;
				if (this.shop) {

					var book_ids = this.shop.book_ids;
					var book_counts = this.shop.book_count;

					var index = book_ids.indexOf(book.id);
					if (index != -1) {
						var count = book_counts[index];
						count = count + 1;
						book_counts.splice(index,1,count);
						this.shop.book_counts = book_counts;
					}else {
						book_ids.push(book.id)
						book_counts.push(1)
						this.shop.book_ids = book_ids;
						this.book_counts = book_counts;
					}
					
				}else {
					that.shop = {
						book_ids : [book.id],
						book_count : [1]
					}
				}
				
				 var param = {
					phone: user.phone,
					book_ids: JSON.stringify(that.shop.book_ids),
					book_count: JSON.stringify(that.shop.book_count)
				}
				this.axios.post('/v1/order/add',param
				).then((response) => {
					if (response.data.code == 1000) {
						that.shopCount = that.shopCount + 1;
						this.$message({
							message: '添加购物车成功',
							type: 'success'
						});
					}else if (response.data.code == -1){
						that.$message.error(response.data.msg);
						that.getShop();
					}else {
						that.$message.error(response.data.error);
						that.getShop();
					}
				}).catch((response) => {
					that.$message.error("请求出错");
					that.getShop();
				})

			}else {
				this.$router.push('Login')
			}
			
		},
		showDetail: function(book) {
			this.selectBook = book;
			this.detailDialogVisible = true;
		},
		goShop:function() {
			this.getShop(true);
		},
		loginAction:function() {
			this.$router.push('Login')
		}
    }
}
</script>

<style>

.home {
	position: relative;
}

/* 顶部 */
.header {
	height: 80px;
	top: 0;
	width: 100%;
	background-color: aliceblue;
	position: relative;
}

.login_status {
	margin-left: 10px;
	color: tomato;
	cursor: pointer;
}

.login_info {
	display: flex;
	top: 0;
	right: 0;
	position: absolute;
    width: 30%;
	height: 100%;
	align-items: center;
	justify-content: flex-end;
	margin-right: 100px;
}

.login_item {
    display: flex;
    align-items: center;
    margin-top: 20px;
}

.login_title {
    width: 80px;
}

.login_input {
    width: 200px;
}

/* 搜索栏 */

.header_handle {
	height: 100px;
	display: flex;
	align-items: center;
	justify-content: center;
}

.search_box {
	display: flex;
	margin-left: 250px;
}

.search_box input{
	height: 50px;
}

.search_input {
	width: 400px;
	margin-right: 20px;
}

.mine_box {
	margin-left: 40px;
	display: flex;
}

.shop_btn {
	height: 50px;
}

.shop_count {
	margin-left: 15px;
	height: 50px;
	text-align: center;
	line-height: 50px;
}

/* 类别 */
.category {
	display: flex;
	position: absolute;
	left: 0;
	top: 0;
	flex-direction: column;
	width: 160px;
	align-items: center;
	bottom: 0;
	padding-top: 200px;
}

.active {
	margin-bottom: 20px;
	color: rgb(255, 0, 85);
	cursor:pointer;
}

.category_item {
	margin-bottom: 20px;
	cursor:pointer;
}


/* 图书内容 */

.bookContent {
	position: absolute;
	left: 160px;
	display: flex;
	flex-wrap: wrap;
	padding: 60px;
	top: 200px;
}

.book_box {
	width: 300px;
	height: 460px;
	margin: 60px;
}

.book_img {
	width: 200px;
	height: 300px;
	display: inline-block;
}

.page_content {
	z-index: 99;
	height: 80px;
}

.book_title {
	font-weight: 500;
	margin-top: 15px;
	font-size: 22px;
	margin-bottom: 10px;
	height: 30px;
	white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
}

.book_des {
	height: 30px;
	white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
}

.amount_box {
	margin-bottom: 10px;
}

.book_price {
	font-weight: 600;
	margin-top: 15px;
}

/* 详情 */

.dialog_content {
	padding: 20px 40px;
}

.detail_header {
	display: flex;
}

.detail_img {
	width: 230px;
	height: 300px;
	display: inline-block;
}


.detail_content {
	margin-left: 30px;
	margin-top: 20px;
}

.detail_title {
	font-weight: 600px;
	font-size: 26px;
}

.detail_tags {
	margin-top: 10px;
	margin-bottom: 10px;
}

.detail_price {
	font-size: 24px;
	font-weight: 500;
	margin-bottom: 20px;
}

.detail_sub {
	margin-top: 20px;
}

.shop_count_value {
	color: tomato;
	font-size: 22px;
	font-weight: 500;
	margin-left: 5px;
}

</style>