export interface ProductRequest {
  sku: string;                  
  name: string;                 
  shortDescription: string;     
  description: string;       
  image: File | null;   
  basePriceCents: number;       
  purchasePriceCents: number;   
  salePriceCents: number;       
  taxRate: number;              
  stock: number;                
  categoryId: number;           
  discountId: number;           
  active: boolean;              
}