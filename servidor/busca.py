#!/usr/bin/python2.7
#-*- coding: utf-8 -*-

import requests as req
import json
import socket

def pesquisar_ex(pesquisar, cabecalho, site):

  print "******SUPERMERCADO 1********"

  url=site+pesquisar

  r = req.get(url, headers=cabecalho)

  resultados=json.loads(r.text)

  titulo = resultados['results'][0]['title']
  preco  = float(resultados['results'][0]['price'].replace(',','.'))
  link   = resultados['results'][0]['url']
  cat    = resultados['results'][0]['shelfname']
  disp   = int(resultados['results'][0]['instock'])
  img    = resultados['results'][0]['image_url']

  #if preco >= 3 and preco <= 10:
  #  print "O preco esta entre 3 e 10 -  %2f" % preco 

  print "\n\tProduto:  ",titulo
  print "\tCategoria:",cat
  print "\tPreço:     R$",preco
  print "\tImagem:   ", img
  print "\tLink:     ",link

  if disp == 1:
    print "\t** Está disponivel no estoque! **"
  else:
    print "\t** Não tem mais no estoque. :\\ **"
  print

def pesquisar_pa(pesquisar, cabecalho, site):
  
  print "******SUPERMERCADO 2********"

  url = site+pesquisar

  r = req.get(url, headers=cabecalho)

  resultados=json.loads(r.text)

  titulo = resultados['results'][0]['title']
  preco  = float(resultados['results'][0]['price'].replace(',','.'))
  link   = resultados['results'][0]['url']
  cat    = resultados['results'][0]['shelfname']
  disp   = int(resultados['results'][0]['instock'])
  img    = resultados['results'][0]['image_url']

  #if preco >= 3 and preco <= 10:
  #  print "O preco esta entre 3 e 10 -  %2f" % preco

  print "\n\tProduto:  ",titulo
  print "\tCategoria:",cat
  print "\tPreço:     R$",preco
  print "\tImagem:   ", img
  print "\tLink:     ",link

  if disp == 1:
    print "\t** Está disponivel no estoque! **"
  else:
    print "\t** Não tem mais no estoque. :\\ **"
  print

def pesquisar_teste():
  
  print "******SUPERMERCADO XYZ********"
  r=''

  with open('pi2teste.txt', 'r') as f:
    r=f.read().decode('utf-8')
  
  resultados=json.loads(r)

  titulo = resultados['results'][0]['title']
  preco  = float(resultados['results'][0]['price'].replace(',','.'))
  link   = resultados['results'][0]['url']
  cat    = resultados['results'][0]['shelfname']
  disp   = int(resultados['results'][0]['instock'])
  img    = resultados['results'][0]['image_url']

  #if preco >= 3 and preco <= 10:
  #  print "O preco esta entre 3 e 10 -  %2f" % preco

  print "\n\tProduto:  ",titulo
  print "\tCategoria:",cat
  print "\tPreço:     R$",preco
  print "\tImagem:   ", img
  print "\tLink:     ",link

  if disp == 1:
    print "\t** Está disponivel no estoque! **"
  else:
    print "\t** Não tem mais no estoque. :\\ **"
  print


def teste_servidor():

  # teste inicial para este servidor
  host = "localhost"
  port = 5000 

  s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
  s.bind((host, port))
  s.listen(1)

  print("Aguardando conexao...")

  conn, addr = s.accept()

  print("IP CONECTADO: ", addr)

  while True:
    data = conn.recv(1024)

    if("sair" in repr(data)):
      conn.close()
      break
    else:
      print(repr(data))

  s.close()

def main():

  #pesquisar=raw_input("Produto para fazer a pesquisa: ").replace(" ", "+").lower()

  #site_extra=''
  #site_pao=''
  
  #with open('config.cfg', 'r') as f: 
  #  leitura=f.readlines()    
  #  site_ex=leitura[0].rstrip('\n')
  #  site_pa=leitura[1].rstrip('\n')
  #  agente=leitura[2].rstrip('\n')

  #cabecalho = {"User-Agent":agente}

  #pesquisar_ex(pesquisar, cabecalho, site_ex)
  #pesquisar_pa(pesquisar, cabecalho, site_pa)

  #teste_servidor()

  pesquisar_teste()

main()
