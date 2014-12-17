$(document).ready(function(){
initiate();
});

function initiate(){
  dataToView(disks,$("#disc"));
  dataToView(flashDisks,$("#u-disc"));
  dataToView(hardDisks,$("#hard-disc"));
}

function dataToView(products,container){
  container.empty();
  var proLis = _.map(products,function(product){
    return proLi(product);
  })
  _.each(proLis,function(li){
    container.append(li);
  })
}

function proLi(product){
  return $("<li>").append(proTitle(product.name))
                   .append(proImg(product.img))
                   .append(proDesc(product.desc))
                   .append(proDel(product.name))
                   .append(proAdd(product.name));
}


function proTitle(title){
  return $("<p>").attr("class","center")
                  .append("<strong>"+title+"</strong>");
}
function proImg(img){
  return $("<img>").attr("src",img);
}
function proDesc(desc){
  return $("<p>").html(desc);
}
function proDel(title){
  return $("<button>").attr("onclick","deleteProduct('"+title+"')").html("delete");
}
function proEdit(title){
  return $("<button>").attr("id",title).attr("onclick","editProduct($(this))").html("edit");
}
function proAdd(title){
  return $("<button>").attr("onclick","addToCart('"+title+"')").html("add to cart");
}
function add(container,products){
  var newproduct = {name:$("#"+container+"Name").val(),
                    img:$("#"+container+"Img").val(),
                    desc:$("#"+container+"Desc").val()};
  products.push(newproduct);
  dataToView(products,$("#"+container));
}

function search(container,products){
  var keyword = $("#"+container+"Search").val();
  var searchResults = _.filter(products,function(product){
    return product.name.indexOf(keyword)!= -1;
  })
  dataToView(searchResults,$("#"+container));
}

function deleteProduct(proName){
  disks = _.reject(disks,function(disk){
    return disk.name == proName;
  })
  flashDisks = _.reject(flashDisks,function(flashDisk){
    return flashDisk.name == proName;
  })
  hardDisks = _.reject(hardDisks,function(hardDisk){
    return hardDisk.name == proName;
  })
  dataToView(disks,$("#disc"));
  dataToView(flashDisks,$("#u-disc"));
  dataToView(hardDisks,$("#hard-disc"));
}

function editProduct(container){
  console.log(container.attr("id"));
}
