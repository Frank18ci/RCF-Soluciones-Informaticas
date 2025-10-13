import Product from "./model/Product";
import ProductAttribute from "./model/ProductAttribute";

export default interface ProductAttributeValue {
  id: number;
  value: string;
  product: Product;
  productAttribute: ProductAttribute;
}