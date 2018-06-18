#!/usr/bin/python3
#-*- coding: utf-8 -*-

try:
    from configurar import *
           
    import json
    from requests import get

    def pesquisar(busca, cabecalho, site, banco):

        for mercado in site:  
        
            url = site[mercado]+busca
            r = get(url, headers=cabecalho)
            resultados=json.loads(r.text)
            
            total_prod = resultados['results'].__len__()
            
            for p in range(0,total_prod):

                prod = resultados['results'][p]
                inserir_dados(prod, mercado, banco)



    def inserir_dados(prod, mercado, banco):

        titulo = prod['title']
        url   = prod['url']
        disp   = int(prod['instock'])
        img    = prod['image_url']    

        if( ('price' in prod) and ('categorias' in prod) ):
            preco  = float(prod['price'].replace(',','.'))
            cat  = prod['categorias'][0]

            if mercado == "extra":

                extra_cat = dict()
                extra_cat["cerveja"] = ["Cervejas", "Cervejas Especiais"]
                extra_cat["leite"] = ["Leite tradicional", "Biscoitos doces"]
                extra_cat["molhos"] = ["Atomatados", "Molhos especiais"]

                if(cat in extra_cat["cerveja"]):
                    categoria = 'cerveja'
                elif(cat in extra_cat["leite"]):
                    categoria = 'leite'
                elif(cat in extra_cat["molhos"]):
                    categoria = 'molhos'
                else:
                    categoria = ''

            elif mercado == "pao":

                pao_cat = dict()
                pao_cat["cerveja"] = ["Cervejas", "Cervejas Especiais"]
                pao_cat["leite"] = ["Leite e Achocolatado", "Biscoitos Doces"]
                pao_cat["molhos"] = ["Molhos de Tomate", "Molhos Diversos"]

                if(cat in pao_cat["cerveja"]):
                     categoria = "cerveja"
                elif(cat in pao_cat["leite"]):
                    categoria = "leite"
                elif(cat in  pao_cat["molhos"]):
                    categoria = "molhos"
                else:
                    categoria = ''

            if categoria != '':
                dados = {"titulo": titulo, 
                         "imagem": img, 
                         "preco": preco, 
                         "disp": disp, 
                         "url": url, 
                         "categoria": cat}
                banco.child(mercado).child(categoria).child(cat).push(dados)


    def main():

        print("\n--==[ Projeto Integrador II ]==--\n")

        print(".: Iniciando a população do banco\n")

        produtos_pesquisar = ['leite', 'cerveja', 'molhos'] 

        for busca in produtos_pesquisar:
            pesquisar(busca,cabecalho,site, banco)

        print("   Processo de população concluída! :.\n")


    if __name__ == "__main__":
        main()

except ImportError:
    print("\n\tPrecisa do arquivo de configuração!\n")

