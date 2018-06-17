#!/usr/bin/python3
#-*- coding: utf-8 -*-

import pyrebase
import requests as req
import json
import socket

def pesquisar_ex(pesquisar, cabecalho, site, db):

  print("******EXTRA********")

  url = site+pesquisar
  r = req.get(url, headers=cabecalho)
  resultados=json.loads(r.text)

  print("Quantidade de produtos buscados: ", resultados['results'].__len__())

  for p in range(0,resultados['results'].__len__()):
    titulo = resultados['results'][p]['title']
    if('price' in resultados['results'][p]):
        preco  = float(resultados['results'][p]['price'].replace(',','.'))
    else:
        preco = 0.0
    link   = resultados['results'][p]['url']
    if('categorias' in resultados['results'][p]):
      cat  = resultados['results'][p]['categorias'][0]
    else:
      cat = "null"
    disp   = int(resultados['results'][p]['instock'])
    img    = resultados['results'][p]['image_url']

    if(cat in ('Cervejas', 'Cervejas Especiais')):
      categoria = 'cerveja'
    elif(cat in ('Leite tradicional', 'Biscoitos doces')):
      categoria = 'leite'
    elif(cat in ('Atomatados', 'Molhos especiais')):
      categoria = 'molhos'
    else:
      categoria = ''

    if(categoria !=  ''):
      dados = {"titulo": titulo, "imagem": img, "preco": preco, 'disp': disp, 'url': link, 'categoria': cat}
      db.child("extra").child(categoria).child(cat).push(dados)


def pesquisar_pa(pesquisar, cabecalho, site, db):

  print("******PAO DE ACUCAR********")

  url = site+pesquisar
  r = req.get(url, headers=cabecalho)
  resultados=json.loads(r.text)

  print("Quantidade de produtos buscados: ", resultados['results'].__len__())

  for p in range(0,resultados['results'].__len__()):
    titulo = resultados['results'][p]['title']
    if('price' in resultados['results'][p]):
        preco  = float(resultados['results'][p]['price'].replace(',','.'))
    else:
        preco = 0.0
    link   = resultados['results'][p]['url']
    if('categorias' in resultados['results'][p]):
      cat  = resultados['results'][p]['categorias'][0]
    else:
      cat = "null"
    disp   = int(resultados['results'][p]['instock'])
    img    = resultados['results'][p]['image_url']

    if(cat in ('Cervejas', 'Cervejas Especiais')):
      categoria = 'cerveja'
    elif(cat in ('Leite e Achocolatado', 'Biscoitos Doces')):
      categoria = 'leite'
    elif(cat in ('Molhos de Tomate', 'Molhos Diversos')):
      categoria = 'molhos'
    else:
      categoria = ''

    if(categoria !=  ''):
      dados = {"titulo": titulo, "imagem": img, "preco": preco, 'disp': disp, 'url': link, 'categoria': cat}
      db.child("pao").child(categoria).child(cat).push(dados)

def main():

  #pesquisar=input("Produto para fazer a pesquisa: ").replace(" ", "+").lower()

  site_extra=''
  site_pao=''

  with open('config.cfg', 'r') as f:
    leitura=f.readlines()
    site_ex=leitura[0].rstrip('\n')
    site_pa=leitura[1].rstrip('\n')
    agente=leitura[2].rstrip('\n')

    config = {
      "apiKey": leitura[3].rstrip('\n'),
      "authDomain": leitura[4].rstrip('\n'),
      "databaseURL": leitura[5].rstrip('\n'),
      "storageBucket": leitura[6].rstrip('\n'),
      "serviceAccount": leitura[7].rstrip('\n')
    }

  cabecalho = {"User-Agent":agente}

  firebase = pyrebase.initialize_app(config)

  db = firebase.database()

  produtos_pesquisar = ['leite', 'cerveja', 'molhos']

  for produtor in produtos_pesquisar:
    pesquisar_ex(produto, cabecalho, site_ex, db)
    pesquisar_pa(produto, cabecalho, site_pa, db)

main()
