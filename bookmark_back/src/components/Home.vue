<template>
    <div class="home">

		<div class="header_title">图书商城后台管理系统</div>
		<div class="header_handle">
			<div>
				<el-select v-model="cat_value" placeholder="请选择">
					<el-option
					v-for="item in category"
					:key="item.id"
					:label="item.title"
					:value="item.categoryId">
					</el-option>
				</el-select>
				<el-button class="search_btn" type="success" @click="searchAction" v-if="!isSearch">搜索</el-button>
				<el-button class="search_btn" type="success" @click="unsearchAction" v-if="isSearch">取消搜索</el-button>
			</div>
			<div class="header_action">
				<el-button type="success" @click="addBook">新增图书</el-button>
			</div>
		</div>

		<!-- 表格 -->
		<div>
				<el-table
				:data="books"
				:cell-style="transactionCellstyle"
				:header-cell-style="headerStyle"
				style="width: 100%">
				<el-table-column
					prop="img"
					label="封面"
					>
					<template slot-scope="scope">
						<img class="book_img" :src="scope.row.img" alt="">
					</template>
				</el-table-column>
				<el-table-column
					prop="title"
					label="书名"
					>
				</el-table-column>
				<el-table-column
					prop="tags"
					label="标签"
					>
				</el-table-column>
				<el-table-column
					prop="price"
					label="价格(￥)">
				</el-table-column>
				<el-table-column
					prop="price"
					label="操作">
					<template slot-scope="scope">
						<el-button type="primary" icon="el-icon-edit" circle @click="editBook(scope.row)"></el-button>
						<el-button type="primary" @click="showDetail(scope.row)">查看详情</el-button>
						<el-button type="danger" @click="deleteBook(scope.row)">删除</el-button>
					</template>
				</el-table-column>
				</el-table>
		</div>

		<!-- 页码 -->
		<div class="page_content">
			<el-pagination
				@size-change="handleSizeChange"
				@current-change="handleCurrentChange"
				:current-page="page"
				:page-sizes="[10, 15, 20, 30]"
				:page-size="pageSize"
				layout="total, sizes, prev, pager, next, jumper"
				:total="totleCount">
			</el-pagination>
		</div>

		<!-- 详情 -->
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
			</span>
		</el-dialog>

		<!-- 新增 -->
		<el-dialog
			title="新增图书"
			:close-on-click-modal = false
			:visible.sync="eidctDialogVisible"
			width="900px"
			center>
			<span slot="title">{{ !isEdit ? "新增图书":"修改图书"}}</span>
			<div class="dialog_content">
				<div class="book_item">
					<div class="book_item_title">图书名字</div>
					<el-input class="book_item_input" v-model="selectBook.title" placeholder="请输入书名" clearable></el-input>
				</div>
				<div class="book_item" v-if="!isEdit">
					<div class="book_item_title">图书类别</div>
					<el-select v-model="add_cat_value" placeholder="请选择">
						<el-option
							v-for="item in category"
							:key="item.id"
							:label="item.title"
							:value="item.categoryId">
						</el-option>
					</el-select>
				</div>
				<div class="book_item">
					<div class="book_item_title">图书封面</div>
					<div class="book_img_box">
						
						<el-input class="book_img_input"
							type="textarea"
							:rows="4"
							placeholder="请输入图片地址"
							v-model="selectBook.img" clearable>
						</el-input>
						<img class="book_img_content" :src="selectBook.img" alt="">
					</div>
				</div>
				<div class="book_item">
					<div class="book_item_title">图书标签</div>
					<el-input class="book_item_input" v-model="selectBook.tags" placeholder="请输入标签" clearable></el-input>
				</div>
				<div class="book_item">
					<div class="book_item_title">图书价格</div>
					<el-input class="book_item_input" v-model="selectBook.price" placeholder="请输入价格单位为分" clearable></el-input>
				</div>
				<div class="book_item">
					<div class="book_item_title">图书描述</div>
					<el-input class="book_item_input" v-model="selectBook.sub1" placeholder="请输入描述" clearable></el-input>
				</div>
				<div class="book_item">
					<div class="book_item_title">图书简介</div>
					<div>
						<el-input class="book_sub2_input"
							type="textarea"
							:rows="5"
							placeholder="请输入简介"
							v-model="selectBook.sub2" clearable>
						</el-input>
					</div>
				</div>
				
			</div>
			<span slot="footer" class="dialog-footer">
				<el-button @click="eidctDialogVisible = false">取 消</el-button>
    			<el-button type="success" @click="confirmAction">确 定</el-button>
			</span>
		</el-dialog>

		<el-dialog
			title="提示"
			:visible.sync="deleteDialogVisible"
			width="400px"
			center>
			<span>确定删除这边书吗？</span>
			<span slot="footer" class="dialog-footer">
				<el-button type="danger" @click="deleteAction">确 定</el-button>
				<el-button @click="deleteDialogVisible = false">取 消</el-button>
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
            cat_value: '',
            category: [],
			books:[],
			page:1,
			pageSize: 10,
			totleCount:0,
			selectBook: {},
			detailDialogVisible: false,
			eidctDialogVisible: false,
			isEdit: false,
			isSearch: false,
			add_cat_value: '',
			deleteDialogVisible: false
        }
	},
	mounted() {
		this.getCategory()
	},
    methods: {
		unsearchAction: function() {
			this.isSearch = false;
			this.cat_value = "";
			this.getBooks();
		},

		searchAction: function() {

			var that = this;
			this.isSearch = true;
			this.axios.get('/v1/book/list',{
				params: {
					category: that.cat_value,
					pageSize: that.pageSize,
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
		addBook: function() {
			this.isEdit = false
			this.selectBook = {
				category: "",
				img: "",
				price: "",
				sub1: "",
				sub2: "",
				tags: "",
				title: ""
			}
			this.add_cat_value = ""
			this.eidctDialogVisible = true
		},
		editBook: function(book) {
			this.isEdit = true
			this.add_cat_value = ""
			this.selectBook = JSON.parse(JSON.stringify(book))
			this.selectBook.price = parseInt(this.selectBook.price) * 100
			this.eidctDialogVisible = true
		},
		deleteBook: function(book) {
			this.selectBook = book;
			this.deleteDialogVisible = true;
		},
		deleteAction: function() {
			var that = this;
			var param = {
				id: this.selectBook.id,
			}
			this.axios.post('/v1/book/delete',param
			).then((response) => {
			if (response.data.code == 1000) {
				that.$message({
					message: '删除成功',
					type: 'success'
				});
				that.getBooks()
				that.deleteDialogVisible = false
			}else if (response.data.code == -1){
				that.$message.error("删除出错");
			}else {
				that.$message.error("删除出错");
			}
			}).catch((response) => {
				that.$message.error("请求出错");
			})
		},
		confirmAction: function() {
			var that = this;
			if (this.selectBook.title.length == 0) {
				this.$message.error('图书名称不能为空');
				return;
			}

			if (!this.isEdit) {
				if (this.add_cat_value.length == 0) {
					this.$message.error('图书类别不能为空');
				return;
			}
			}
			

			if (this.selectBook.price.length == 0) {
				this.$message.error('图书价格不能为空');
				return;
			}

			if (!parseInt(this.selectBook.price)) {
				this.$message.error('请输入合理的价格，注：单位为(分)');
				return;
			}

			if (this.isEdit) {
				var param = {
					id: this.selectBook.id,
					title: this.selectBook.title,
					tags: this.selectBook.tags,
					sub1: this.selectBook.sub1,
					sub2: this.selectBook.sub2,
					img: this.selectBook.img,
					price: this.selectBook.price
				}
				this.axios.post('/v1/book/update',param
				).then((response) => {
					if (response.data.code == 1000) {
						that.$message({
							message: '更新成功',
							type: 'success'
						});
						that.getBooks()
						that.eidctDialogVisible = false
					}else if (response.data.code == -1){
						that.$message.error("更新出错");
					}else {
						that.$message.error("添加出错");
					}
				}).catch((response) => {
					that.$message.error("请求出错");
				})
			}else {
				var param = {
					category: this.add_cat_value,
					title: this.selectBook.title,
					tags: this.selectBook.tags,
					sub1: this.selectBook.sub1,
					sub2: this.selectBook.sub2,
					img: this.selectBook.img,
					price: this.selectBook.price
				}
				this.axios.post('/v1/book/add',param
				).then((response) => {
					if (response.data.code == 1000) {
						that.$message({
							message: '添加成功',
							type: 'success'
						});
						that.getBooks()
						that.eidctDialogVisible = false
					}else if (response.data.code == -1){
						that.$message.error("添加出错");
					}else {
						that.$message.error("添加出错");
					}
				}).catch((response) => {
					that.$message.error("请求出错");
				})
			}
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
		getBooks: function() {
			var that = this;
			this.axios.get('/v1/book/all',{
				params: {
					pageSize: that.pageSize,
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
		handleSizeChange(val) {
			this.pageSize = val;
			this.getBooks();
		},
		showDetail: function(book) {
			this.selectBook = book;
			this.detailDialogVisible = true;
		},
		transactionCellstyle({row, column, rowIndex, columnIndex}){
            return "text-align:center";
        },
        headerStyle({row, column, rowIndex, columnIndex}){
            return "text-align:center";
        }
    }
}
</script>

<style>

.home {
	position: relative;
	padding: 20px 100px;
}

.header_title {
	margin-top: 20px;
	margin-bottom: 60px;
}

/* 顶部 */
.header_handle {
	display: flex;
	justify-content: center;
	background-color: aliceblue;
	height: 100px;
	align-items: center;
}

.search_btn {
	margin-left: 20px;
}

.header_action {
	margin-left: 40px;
}

/*  */

.book_img {
	width: 60px;
	height: 80px;
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

.page_content {
	margin-top: 40px;
}

/* 编辑 新增 */
.book_item {
	display: flex;
	margin-bottom: 20px;
}

.book_item_title {
	margin-right: 60px;
	width: 100px;
	/* font-size: 20px; */
	font-weight: 500;
}

.book_img_box {
	display: flex;
	align-items: center;
}

.book_item_input {
	width: 200px;
}

.book_img_content {
	width: 60px;
	height: 80px;
}

.book_img_input {
	width: 300px;
	margin-right: 15px;
}

.book_sub2_input {
	width: 400px;
}


</style>