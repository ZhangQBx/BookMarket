<template>
    <div class="shop">
        <div class="header">
            <div class="title">购物车</div>
            <div class="login_info">
				<div>欢迎光临网上书城</div>
				<div class="login_status" v-if="!user">请登录</div>
				<div class="login_status" v-if="user">{{user.phone}}</div>
			</div>
            <div class="back" @click="goHome">返回首页</div>
        </div>
        <div class="book_title_header"  v-show="shopList.length > 0">
            <div class="header_title">
                <div>图书</div>
            </div>
            <div class="heade_des">
                <div>价格</div>
                <div class="book_count_title">数量</div>
                <div>总价</div>
            </div>
        </div>
        <div class="book_list" v-show="shopList.length > 0">

            <div class="book" v-for="(item,index) in shopList" :key="index">
                <div class="book_header">
                    <img class="book_img" :src="item.img" alt="">
                    <div class="book_des">
                        <div class="book_title">{{item.title}}</div>
                        <!-- <div class="book_tags">{{item.tags}}</div> -->
                        <div class="book_sub1">{{item.sub1}}</div>
                    </div>
                    
                </div>
                <div class="book_end">
                    <div class="book_price">￥{{item.unit}}</div>
                    <div class="book_count_content">
                        
                        <el-input-number class="count_input" v-model="item.count" @change="handleChange(index,item)" :min="1" :max="99" label="描述文字"></el-input-number>
                    </div>
                    
                    <div class="book_amount">￥{{item.amount}}</div>
                </div>
                <el-button class="delete_btn" type="danger" @click="deleteBook(index,item)">删除</el-button>
            </div>

        </div>

        <div class="book_none" v-show="shopList.length == 0">
            购物车空空如也
        </div>

        <div class="book_footer">
            <div class="foot_count">共{{totleCount}}件商品</div>
            <div class="foot_amount">
                <span>总价</span>
                <span class="totle_amount">￥{{totleAmount}}</span>
            </div>
            <!-- <el-button type="primary" @click="saveShop">保存购物车</el-button> -->
        </div>

    </div>
</template>

<script>

export default {

    data() {
        return {
            shop: {},
            shopList: [],
            totleAmount: 0,
            totleCount: 0,
            user: {}
        }
    },

    mounted() {
        // debugger
        var shop = JSON.parse(localStorage.getItem('shop'))
        var user = JSON.parse(localStorage.getItem('user'));
		this.user = user;

        var list = [];
        var totleAmount = 0;
        var totleCount = 0;
        if (shop && shop.books) {
            debugger
            for (var i = 0; i < shop.books.length; i++) {
                var book = shop.books[i];
                var count = shop.book_count[i]
                book.count = count
                book.unit = (book.price / 100).toFixed(2)
                var amount = count * book.price / 100
                book.amount = amount.toFixed(2);
                totleAmount = amount + totleAmount
                totleCount = count + totleCount
                list.push(book)
            }
            this.shopList = list;
            this.totleAmount = totleAmount.toFixed(2);
            this.totleCount = totleCount
            this.shop = shop;
        }
    },

    methods: {
        handleChange: function(index,item) {

            var amount = item.count * item.price / 100
            item.amount = amount.toFixed(2);
            // debugger
            var book_counts = this.shop.book_count;
            var o_count = book_counts[index];

            var i = item.count - o_count;
            
            this.totleCount = this.totleCount + i;
            this.totleAmount = (parseInt(this.totleAmount) + i * item.price / 100).toFixed(2) 

            var count = item.count;
            book_counts.splice(index,1,count);
            this.shop.book_count = book_counts;

            this.saveShop()
            // console.log(this.shop)
            // console.log(item)


        },
        saveShop: function() {

            var that = this;
            var param = {
                phone: this.user.phone,
                book_ids: JSON.stringify(that.shop.book_ids),
                book_count: JSON.stringify(that.shop.book_count)
            }
            this.axios.post('/v1/order/add',param
            ).then((response) => {
                if (response.data.code == 1000) {
                    that.shopCount = that.shopCount + 1;
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
        },
        deleteBook: function(index,item) {
            this.totleCount = this.totleCount - item.count;
            this.totleAmount = (parseInt(this.totleAmount) - item.count * item.price / 100).toFixed(2) 

            this.shop.book_ids.splice(index,1);
            this.shop.book_count.splice(index,1);
            this.shopList.splice(index,1);
            this.saveShop();
        },
        goHome: function() {
            this.$router.go(-1)
        }
    }

}

</script>

<style scoped>

.header {
	height: 80px;
	top: 0;
	width: 100%;
	background-color: aliceblue;
	position: relative;
}
.title {
    height: 80px;
    line-height: 80px;
    font-size: 22px;
    font-weight: 500;
}   

.login_status {
	margin-left: 10px;
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

.back {
    position: absolute;
    right: 0;
    top: 0;
    height: 100%;
    line-height: 80px;
    cursor: pointer;
}

.book_list {
    margin-left: 240px;
    margin-right: 240px;
    margin-bottom: 80px;
}

.book {
    position: relative;
    height: 240px;
    padding: 40px 80px;
    display: flex;
    justify-content: space-between;
}

.book_img {
    width: 160px;
    height: 200px;
}

.book_header {
    display: flex;
    height: 240px;
}

.book_end {
    display: flex;
}

.book_des {
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: flex-start;
    margin-left: 40px;
    width: 300px;
}

.book_title {
    font-size: 22px;
    font-weight: 500;
    margin-top: 10px;
}

.book_tags {
    margin-top: 20px;
    margin-bottom: 10px;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
}

.book_sub1 {
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    margin-top: 20px;
}

.book_end {
    /* margin-top: -60px; */
    align-items: center;
    height: 140px;
}

.book_count_content {
    display: flex;
    width: 140px;
    margin-left: 60px;
    margin-right: 60px;
    /* padding: 0 20px; */
    /* justify-content: center; */
}

.count_input {
    /* width: 160px; */
}

.book_title_header {
    margin-top: 20px;
    display: flex;
    height: 60px;
    align-items: center;
    justify-content: space-between;
    padding: 0 320px;
}

.header_title {
    margin-left: 200px;
}

.heade_des {
    display: flex;
    margin-right: 40px;
}

.book_count_title {
    margin-left: 120px;
    margin-right: 120px;
}

.book_amount {
    width: 100px;
}

.book_footer {
    position: fixed;
    bottom: 0;
    height: 100px;
    width: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    box-shadow: 0px -5px 3px #eeeeee;
    background-color: white;
    z-index: 999;
}

.totle_amount {
    font-size: 24px;
    font-weight: 500;
    color: red;
}

.foot_count {
    margin-left: 200px;
}

.foot_amount {
    margin-left: 40px;
    margin-right: 40px;
}

.delete_btn {
    position: absolute;
    right: -80px;
    top: 90px;
}

.book_none {
    margin-top: 200px;
}

</style>