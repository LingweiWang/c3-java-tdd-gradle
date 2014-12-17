function addToCartButton(name) {
    return $("<button>")
        .html("add to cart")
        .click(function () {
            addToCart(name);
        });
}

// function showLittleCart(){
//   $(".cart").css("display","block");
// }
// function hideLittleCart(){
//   $(".cart").mouseout(function(){
//     var target = event.srcElement.id;
//     if(target == cart)
//     $(".cart").css("display","none");
//   })
//     // var target = event.srcElement.id ;
//     // console.log(target);
//     // if(target == "nogoods" || target == "products" || target == "cart"){
//     //  $(".cart").css("display","block");
//     //
//     // }
//     //else{
//     //  console.log("out!!!!");
//     //  $(".cart").css("display","none");
//     //}
// }
function addToCart(name) {
  _.each(disks,function(disk){
    if(disk.name == name )
      cart.push(disk);
  })
  _.each(flashDisks,function(flashDisk){
    if(flashDisk.name == name)
      cart.push(flashDisk)
  })
  _.each(hardDisks,function(hardDisk){
    if(hardDisk.name == name)
      cart.push(hardDisk);
  })
  dataToCartView(cart,$("#cartUl"));
}
function dataToCartView(products, container){
  container.empty();
  var cartLis = _.map(products,function(product){
    return cartLi(product);
  })
  _.each(cartLis,function(li){
    container.append(li);
  })
}
function cartLi(product){
 return $("<li>").append(proTitle(product.name))
                 .append(proImg(product.img))
                 .append(proDesc(product.desc))
                 .append(cartDel(product.name));
}
function cartDel(title){
  return $("<button>").attr("onclick","deleteProductFromCart('"+title+"')").html("delete");
}


function goToCart() {
  $("#body").css("display","none");
  $("#cartList").css("display","block");
  $("#switch").html("回到首页").attr("href","javascript:goBackToMain()");
}
function goBackToMain(){
  $("#body").css("display","block");
  $("#cartList").css("display","none");
  $("#switch").html("购物车").attr("href","javascript:goToCart()");
}
function deleteProductFromCart(name){
  cart = _.reject(cart,function(c){
    return c.name == name;
  });
  dataToCartView(cart,$("#cartUl"));
}
