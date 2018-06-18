from configparser import ConfigParser
import pyrebase

config = ConfigParser()
config.optionxform = str
config.read('config.ini')

site = config['url']
cabecalho = dict(config['agent'])

firebase = pyrebase.initialize_app( dict(config['firebase']))
banco = firebase.database()

